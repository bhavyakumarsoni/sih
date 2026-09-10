package com.geowatershed.app.data

import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.db.GeoWatershedDatabase
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.db.PhotoEvidenceEntity
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.data.model.PhotoSet
import kotlinx.coroutines.flow.Flow

class GeoWatershedRepository(private val db: GeoWatershedDatabase) {

    fun observeCaptures(): Flow<List<CaptureEntity>> = db.captureDao().observeAll()
    fun observeInterventions(): Flow<List<InterventionEntity>> = db.interventionDao().observeAll()
    fun observeIntervention(id: Long): Flow<InterventionEntity?> = db.interventionDao().observeById(id)
    fun observePhotosForIntervention(id: Long): Flow<List<PhotoEvidenceEntity>> =
        db.photoEvidenceDao().observeForIntervention(id)

    suspend fun getCapture(id: Long): CaptureEntity? = db.captureDao().getById(id)

    suspend fun createCapture(
        observationType: ObservationType,
        description: String,
        latitude: Double?,
        longitude: Double?,
        accuracyMeters: Float?,
        photoPath: String?,
    ): Long {
        val siteCode = "SITE-" + (200 + db.captureDao().count())
        val capture = CaptureEntity(
            siteCode = siteCode,
            timestamp = System.currentTimeMillis(),
            observationType = observationType.name,
            description = description,
            latitude = latitude,
            longitude = longitude,
            accuracyMeters = accuracyMeters,
            photoPath = photoPath,
            priorityScore = SiteAnalysisCalculator.scoreFor(observationType),
        )
        return db.captureDao().insert(capture)
    }

    suspend fun createIntervention(type: String, sourceCaptureId: Long): Long {
        val capture = db.captureDao().getById(sourceCaptureId)
        val intervention = InterventionEntity(
            type = type,
            siteCode = capture?.siteCode ?: "SITE-????",
            siteDetail = (capture?.siteCode ?: "unknown site") + " · created today",
            latitude = capture?.latitude,
            longitude = capture?.longitude,
            accuracyMeters = capture?.accuracyMeters,
            stage = InterventionStage.Proposed.name,
            createdAt = System.currentTimeMillis(),
            sourceCaptureId = sourceCaptureId,
        )
        return db.interventionDao().insert(intervention)
    }

    suspend fun createApprovedIntervention(type: String, sourceCaptureId: Long): Long {
        val capture = db.captureDao().getById(sourceCaptureId)
        val intervention = InterventionEntity(
            type = type,
            siteCode = capture?.siteCode ?: "SITE-????",
            siteDetail = (capture?.siteCode ?: "unknown site") + " · approved today",
            latitude = capture?.latitude,
            longitude = capture?.longitude,
            accuracyMeters = capture?.accuracyMeters,
            stage = InterventionStage.Approved.name,
            createdAt = System.currentTimeMillis(),
            sourceCaptureId = sourceCaptureId,
        )
        return db.interventionDao().insert(intervention)
    }

    suspend fun advanceIntervention(intervention: InterventionEntity) {
        val current = InterventionStage.valueOf(intervention.stage)
        val next = current.next ?: return
        db.interventionDao().update(intervention.copy(stage = next.name))
    }

    suspend fun submitForVerification(intervention: InterventionEntity) {
        db.interventionDao().update(intervention.copy(verificationSubmitted = true))
    }

    suspend fun addPhoto(interventionId: Long, set: PhotoSet, photoPath: String?, date: String, meta: String) {
        db.photoEvidenceDao().insert(
            PhotoEvidenceEntity(
                interventionId = interventionId,
                photoSet = set.name,
                photoPath = photoPath,
                date = date,
                meta = meta,
                createdAt = System.currentTimeMillis(),
            ),
        )
    }

    /** Seeds the original design-mock demo data once, so the app isn't empty on first launch. */
    suspend fun seedIfEmpty() {
        if (db.captureDao().count() > 0 || db.interventionDao().count() > 0) return

        val seedCaptureId = db.captureDao().insert(
            CaptureEntity(
                siteCode = SiteAnalysisCalculator.SEED_SITE_CODE,
                timestamp = System.currentTimeMillis(),
                observationType = ObservationType.SoilErosion.name,
                description = "Gully ~1.2 m deep forming along the north field boundary after last week rain.",
                latitude = 13.13624,
                longitude = 78.13291,
                accuracyMeters = 4.2f,
                photoPath = null,
                priorityScore = 78,
            ),
        )

        val checkDamId = db.interventionDao().insert(
            InterventionEntity(
                type = "Check Dam",
                siteCode = SiteAnalysisCalculator.SEED_SITE_CODE,
                siteDetail = "${SiteAnalysisCalculator.SEED_SITE_CODE} · created today",
                latitude = 13.13624,
                longitude = 78.13291,
                accuracyMeters = 4f,
                stage = InterventionStage.Proposed.name,
                createdAt = System.currentTimeMillis(),
                sourceCaptureId = seedCaptureId,
            ),
        )
        db.interventionDao().insert(
            InterventionEntity(
                type = "Farm Pond",
                siteCode = "SITE-0117",
                siteDetail = "SITE-0117 · 12 Aug",
                latitude = 13.14802,
                longitude = 78.11976,
                accuracyMeters = 6f,
                stage = InterventionStage.UnderConstruction.name,
                createdAt = System.currentTimeMillis(),
                sourceCaptureId = null,
            ),
        )
        db.interventionDao().insert(
            InterventionEntity(
                type = "Contour Trench",
                siteCode = "SITE-0093",
                siteDetail = "SITE-0093 · 28 Jul",
                latitude = 13.12210,
                longitude = 78.14431,
                accuracyMeters = 5f,
                stage = InterventionStage.Monitoring.name,
                createdAt = System.currentTimeMillis(),
                sourceCaptureId = null,
            ),
        )

        val beforeSet = listOf(
            "02 Jul 2026" to "facing NE · ±4 m",
            "02 Jul 2026" to "gully floor · ±4 m",
        )
        val afterSet = listOf(
            "28 Aug 2026" to "facing NE · ±4 m",
        )
        beforeSet.forEach { (date, meta) -> addPhoto(checkDamId, PhotoSet.Before, null, date, meta) }
        afterSet.forEach { (date, meta) -> addPhoto(checkDamId, PhotoSet.After, null, date, meta) }
    }

    companion object {
        @Volatile
        private var instance: GeoWatershedRepository? = null

        fun getInstance(db: GeoWatershedDatabase): GeoWatershedRepository =
            instance ?: synchronized(this) {
                instance ?: GeoWatershedRepository(db).also { instance = it }
            }
    }
}
