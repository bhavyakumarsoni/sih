package com.geowatershed.app.data

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

data class GpsFix(val latitude: Double, val longitude: Double, val accuracyMeters: Float)

/** Wraps the Fused Location Provider for a single one-shot high-accuracy GPS fix. */
class LocationProvider(context: Context) {
    private val client = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): GpsFix? = suspendCancellableCoroutine { cont ->
        val cancellationTokenSource = CancellationTokenSource()
        val request = CurrentLocationRequest.Builder()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
        client.getCurrentLocation(request, cancellationTokenSource.token)
            .addOnSuccessListener { location ->
                cont.resume(location?.let { GpsFix(it.latitude, it.longitude, it.accuracy) })
            }
            .addOnFailureListener {
                cont.resume(null)
            }
        cont.invokeOnCancellation { cancellationTokenSource.cancel() }
    }
}
