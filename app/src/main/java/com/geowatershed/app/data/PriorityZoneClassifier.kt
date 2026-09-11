package com.geowatershed.app.data

import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.data.model.PriorityZone

/**
 * Assigns each captured site one of the five [PriorityZone] values.
 *
 * The rule, in order of precedence:
 *
 *  - work at this site has reached Monitoring   -> Blue  (due for monitoring)
 *  - work at this site is Completed             -> Green (work completed)
 *  - work is Approved or Under Construction     -> Yellow (work in progress)
 *  - no work yet (or only Proposed), score >= 70 -> Red   (high priority)
 *  - no work yet (or only Proposed), score < 70  -> Orange (can be delayed)
 *
 * Sites are matched to interventions by [CaptureEntity.siteCode], and where a
 * site has several interventions the furthest-advanced one decides the zone.
 *
 * Note what this deliberately does NOT do: it never treats an unavailable
 * indicator as a low score, and it never reads "no intervention recorded" as
 * "nothing needed here". Both of those would be the missing-equals-zero
 * mistake wearing a different hat.
 */
object PriorityZoneClassifier {

    /** Matches the HIGH threshold already used by `priorityLevelFor`. */
    const val HIGH_PRIORITY_SCORE = 70

    fun zoneFor(capture: CaptureEntity, interventions: List<InterventionEntity>): PriorityZone {
        val furthestStage = interventions
            .filter { it.siteCode == capture.siteCode }
            .map { InterventionStage.parse(it.stage) }
            .maxByOrNull { it.ordinal }

        return when (furthestStage) {
            InterventionStage.Monitoring -> PriorityZone.Blue
            InterventionStage.Completed -> PriorityZone.Green
            InterventionStage.UnderConstruction -> PriorityZone.Yellow
            InterventionStage.Approved -> PriorityZone.Yellow
            InterventionStage.Proposed, null ->
                if (capture.priorityScore >= HIGH_PRIORITY_SCORE) PriorityZone.Red else PriorityZone.Orange
        }
    }
}
