package com.geowatershed.app.data

import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.data.model.SiteIndicator

/**
 * Turns a [CaptureEntity] into the six site indicators shown on the Site
 * Analysis screen, and scores it. There is no GIS/remote-sensing backend
 * wired up yet, so Slope, Distance to Stream, Vegetation, Existing
 * Structure, and Agricultural Land are honest gaps (UNAVAILABLE) for any
 * capture taken in the field today — only Soil Erosion can be filled in,
 * and only when that's what the field worker actually reported. The demo
 * seed record (SITE-0142) is the one exception, pre-populated with the
 * full backend-style dataset from the original design mock.
 */
object SiteAnalysisCalculator {
    const val SEED_SITE_CODE = "SITE-0142"

    fun indicatorsFor(capture: CaptureEntity): List<SiteIndicator> {
        if (capture.siteCode == SEED_SITE_CODE) return SEED_INDICATORS

        val observationType = runCatching { ObservationType.valueOf(capture.observationType) }.getOrNull()
        val soilErosionValue = if (observationType == ObservationType.SoilErosion) "Reported" else null
        return listOf(
            SiteIndicator("Slope", "pending GIS backend", null),
            SiteIndicator("Distance to Stream", "pending GIS backend", null),
            SiteIndicator("Vegetation", "pending remote-sensing backend", null),
            SiteIndicator("Soil Erosion", "field observation", soilErosionValue),
            SiteIndicator("Existing Structure", "pending survey backend", null),
            SiteIndicator("Agricultural Land", "pending cadastral backend", null),
        )
    }

    /** A provisional score from only the indicators available today — never a fake precise GIS score. */
    fun scoreFor(observationType: ObservationType): Int =
        if (observationType == ObservationType.SoilErosion) 62 else 50

    private val SEED_INDICATORS = listOf(
        SiteIndicator("Slope", "DEM derived · 30 m", "14.2%"),
        SiteIndicator("Distance to Stream", "nearest seasonal channel", "82 m"),
        SiteIndicator("Vegetation", "NDVI · Sentinel-2, Aug", "0.21 sparse"),
        SiteIndicator("Soil Erosion", "field observation", "Severe"),
        SiteIndicator("Existing Structure", "no survey record for this parcel", null),
        SiteIndicator("Agricultural Land", "cadastral layer not published", null),
    )
}
