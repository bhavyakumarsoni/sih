package com.geowatershed.app.data

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.db.GeoWatershedDatabase
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.db.PhotoEvidenceEntity
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.data.model.PhotoSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume

/**
 * Backed by a local Room database (see `data/db/`) — every action here is
 * real and persists across app restarts. There is no remote backend yet:
 * captures/interventions/photos all live only on this device until one is
 * wired up, which is why the dashboard's "queued offline" count is simply
 * the number of local records that have never been synced anywhere.
 */
class GeoWatershedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GeoWatershedRepository.getInstance(GeoWatershedDatabase.getInstance(application))
    private val locationProvider = LocationProvider(application)

    init {
        viewModelScope.launch { repository.seedIfEmpty() }
    }

    // ---- Watershed identity: derived from the device's real location, not
    // a fixed sample name. There is no GIS backend yet, so we can only show
    // what reverse geocoding actually returns — an honest "locating…" /
    // "unavailable" state rather than a fabricated place name. ----
    var watershedName by mutableStateOf("Locating your watershed…")
        private set

    var watershedSub by mutableStateOf("Waiting for a GPS fix")
        private set

    fun resolveWatershedLocation() {
        viewModelScope.launch {
            val fix = locationProvider.getCurrentLocation()
            if (fix == null) {
                watershedName = "Location unavailable"
                watershedSub = "Grant location access to identify this watershed"
                return@launch
            }
            val address = reverseGeocode(fix.latitude, fix.longitude)
            if (address == null) {
                watershedName = "Unnamed watershed"
                watershedSub = formatCoords(fix.latitude, fix.longitude, fix.accuracyMeters)
            } else {
                watershedName = address.subLocality ?: address.locality ?: address.subAdminArea
                    ?: address.adminArea ?: "Unnamed watershed"
                watershedSub = listOfNotNull(
                    address.subAdminArea ?: address.adminArea,
                    address.countryName,
                ).joinToString(" · ").ifBlank { formatCoords(fix.latitude, fix.longitude, fix.accuracyMeters) }
            }
        }
    }

    private suspend fun reverseGeocode(latitude: Double, longitude: Double): Address? {
        val context = getApplication<Application>()
        if (!Geocoder.isPresent()) return null
        val geocoder = Geocoder(context, Locale.getDefault())
        return if (Build.VERSION.SDK_INT >= 33) {
            suspendCancellableCoroutine { cont ->
                geocoder.getFromLocation(latitude, longitude, 1) { addresses ->
                    cont.resume(addresses.firstOrNull())
                }
            }
        } else {
            withContext(Dispatchers.IO) {
                @Suppress("DEPRECATION")
                runCatching { geocoder.getFromLocation(latitude, longitude, 1)?.firstOrNull() }.getOrNull()
            }
        }
    }

    // ---- Governance Mode session. There is no backend and no self-service
    // signup: only a fixed set of officer credentials issued out of band by
    // the BDO office are accepted, and the session lives only in memory. ----
    var isGovernanceAuthed by mutableStateOf(false)
        private set

    var governanceOfficerName by mutableStateOf("")
        private set

    var governanceLoginError by mutableStateOf<String?>(null)
        private set

    fun attemptGovernanceLogin(officerId: String, password: String) {
        val name = GovernanceCredentials.authenticate(officerId, password)
        if (name != null) {
            isGovernanceAuthed = true
            governanceOfficerName = name
            governanceLoginError = null
        } else {
            governanceLoginError = "Incorrect officer ID or password."
        }
    }

    fun governanceLogout() {
        isGovernanceAuthed = false
        governanceOfficerName = ""
        governanceLoginError = null
    }

    val captures: StateFlow<List<CaptureEntity>> = repository.observeCaptures()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val interventions: StateFlow<List<InterventionEntity>> = repository.observeInterventions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun photosFor(interventionId: Long): Flow<List<PhotoEvidenceEntity>> =
        repository.observePhotosForIntervention(interventionId)

    fun interventionFlow(id: Long) = repository.observeIntervention(id)

    // ---- Capture screen: transient state until "Save & Analyze" persists it ----
    var selectedObservation by mutableStateOf(ObservationType.SoilErosion)
        private set

    fun selectObservation(type: ObservationType) {
        selectedObservation = type
    }

    var description by mutableStateOf("")
        private set

    fun updateDescription(text: String) {
        description = text.take(280)
    }

    var gpsFix by mutableStateOf<GpsFix?>(null)
        private set

    var gpsSearching by mutableStateOf(false)
        private set

    fun refreshLocation() {
        viewModelScope.launch {
            gpsSearching = true
            gpsFix = locationProvider.getCurrentLocation()
            gpsSearching = false
        }
    }

    var capturedPhotoPath by mutableStateOf<String?>(null)
        private set

    fun onPhotoCaptured(file: File?) {
        capturedPhotoPath = file?.absolutePath
    }

    /** Persists the current capture-screen state as a new [CaptureEntity] and resets the form. */
    suspend fun saveAndAnalyze(): Long {
        val id = repository.createCapture(
            observationType = selectedObservation,
            description = description,
            latitude = gpsFix?.latitude,
            longitude = gpsFix?.longitude,
            accuracyMeters = gpsFix?.accuracyMeters,
            photoPath = capturedPhotoPath,
        )
        selectedObservation = ObservationType.SoilErosion
        description = ""
        capturedPhotoPath = null
        gpsFix = null
        return id
    }

    suspend fun getCapture(id: Long): CaptureEntity? = repository.getCapture(id)

    fun advanceIntervention(intervention: InterventionEntity) {
        viewModelScope.launch { repository.advanceIntervention(intervention) }
    }

    fun submitForVerification(intervention: InterventionEntity) {
        viewModelScope.launch { repository.submitForVerification(intervention) }
    }

    fun createIntervention(type: String, sourceCaptureId: Long, onCreated: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repository.createIntervention(type, sourceCaptureId)
            onCreated(id)
        }
    }

    /** Governance officer approval: creates the intervention already in the Approved stage, skipping Proposed. */
    fun approveIntervention(type: String, sourceCaptureId: Long, onApproved: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repository.createApprovedIntervention(type, sourceCaptureId)
            onApproved(id)
        }
    }

    fun addPhoto(interventionId: Long, set: PhotoSet, file: File?) {
        viewModelScope.launch {
            val today = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
            val meta = if (file != null) "added just now · on device" else "added just now"
            repository.addPhoto(interventionId, set, file?.absolutePath, today, meta)
        }
    }

    companion object {
        const val EVIDENCE_MINIMUM_PER_SET = 2
    }
}
