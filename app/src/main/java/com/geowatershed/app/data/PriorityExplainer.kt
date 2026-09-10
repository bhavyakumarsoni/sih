package com.geowatershed.app.data

import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.model.SiteIndicator

/** Which way an indicator pushes the priority of a site. */
enum class FactorDirection { Raises, Lowers, Context }

/**
 * One indicator's contribution to a site's classification, in plain words.
 * [reading] is the measured value; [effect] says what it means for priority.
 */
data class PriorityFactor(
    val name: String,
    val reading: String,
    val effect: String,
    val direction: FactorDirection,
)

/**
 * A full account of why a site scored what it scored.
 *
 * [unavailable] is a first-class part of the answer, not an afterthought:
 * a site is never explained as if the data it lacks were evidence of safety.
 */
data class PriorityExplanation(
    val headline: String,
    val contributing: List<PriorityFactor>,
    val unavailable: List<String>,
    val insight: String,
    val caveat: String?,
)

/**
 * Rule-based explainer: turns a capture and its indicators into the reasons
 * behind its priority classification.
 *
 * This is deliberately rule-based and deliberately on-device. It runs with no
 * network, no backend and no model, so it cannot fail during a demo, and
 * every sentence it produces can be traced to a threshold in this file.
 *
 * Thresholds below are conventional watershed-planning rules of thumb, not
 * values calibrated against a validated dataset — treat them as a defensible
 * starting point that a domain expert should review, not as settled science.
 */
object PriorityExplainer {

    // --- thresholds, all in one place so they are easy to argue with ---
    private const val STEEP_SLOPE_PERCENT = 15.0
    private const val MODERATE_SLOPE_PERCENT = 8.0
    private const val NEAR_STREAM_METRES = 150.0
    private const val FAR_STREAM_METRES = 500.0
    private const val SPARSE_NDVI = 0.30
    private const val HEALTHY_NDVI = 0.50

    fun explain(capture: CaptureEntity, indicators: List<SiteIndicator>): PriorityExplanation {
        val contributing = mutableListOf<PriorityFactor>()
        val unavailable = mutableListOf<String>()

        indicators.forEach { indicator ->
            val value = indicator.value
            if (value == null) {
                unavailable += indicator.name
                return@forEach
            }
            factorFor(indicator.name, value)?.let { contributing += it }
        }

        val raising = contributing.count { it.direction == FactorDirection.Raises }
        val level = when {
            capture.priorityScore >= 70 -> "HIGH"
            capture.priorityScore >= 40 -> "MEDIUM"
            else -> "LOW"
        }

        val headline = when {
            contributing.isEmpty() ->
                "Scored $level (${capture.priorityScore}/100) on the field observation alone — " +
                    "no site measurements were available to weigh."
            raising == 0 ->
                "Scored $level (${capture.priorityScore}/100). Nothing measured at this site raises its priority."
            raising == 1 ->
                "Scored $level (${capture.priorityScore}/100), driven by one measured factor."
            else ->
                "Scored $level (${capture.priorityScore}/100), driven by $raising measured factors acting together."
        }

        val caveat = if (unavailable.isEmpty()) {
            null
        } else {
            "${unavailable.size} of ${indicators.size} indicators have no data " +
                "(${unavailable.joinToString(", ")}). They are excluded from this score, " +
                "not counted as zero and not treated as low risk. This site could rank higher " +
                "once they are available."
        }

        return PriorityExplanation(
            headline = headline,
            contributing = contributing,
            unavailable = unavailable,
            insight = insightFor(raising, unavailable.size, indicators.size),
            caveat = caveat,
        )
    }

    private fun factorFor(name: String, value: String): PriorityFactor? = when (name) {
        "Slope" -> firstNumber(value)?.let { slope ->
            when {
                slope >= STEEP_SLOPE_PERCENT -> PriorityFactor(
                    name, value,
                    "Steep enough that runoff gains velocity before it can infiltrate.",
                    FactorDirection.Raises,
                )
                slope >= MODERATE_SLOPE_PERCENT -> PriorityFactor(
                    name, value,
                    "Moderate gradient — runoff builds speed over longer slope lengths.",
                    FactorDirection.Raises,
                )
                else -> PriorityFactor(
                    name, value,
                    "Gentle gradient; slope alone is not driving erosion here.",
                    FactorDirection.Lowers,
                )
            }
        }

        "Distance to Stream" -> firstNumber(value)?.let { metres ->
            when {
                metres <= NEAR_STREAM_METRES -> PriorityFactor(
                    name, value,
                    "Close to a channel, so eroded sediment reaches water with little chance to settle.",
                    FactorDirection.Raises,
                )
                metres >= FAR_STREAM_METRES -> PriorityFactor(
                    name, value,
                    "Far from the nearest channel; sediment has distance to drop out before reaching water.",
                    FactorDirection.Lowers,
                )
                else -> PriorityFactor(
                    name, value,
                    "Mid-range from the nearest channel.",
                    FactorDirection.Context,
                )
            }
        }

        "Vegetation" -> firstNumber(value)?.let { ndvi ->
            when {
                ndvi < SPARSE_NDVI -> PriorityFactor(
                    name, value,
                    "Sparse cover — little root structure holding soil against rainfall.",
                    FactorDirection.Raises,
                )
                ndvi >= HEALTHY_NDVI -> PriorityFactor(
                    name, value,
                    "Healthy cover already binding the surface.",
                    FactorDirection.Lowers,
                )
                else -> PriorityFactor(
                    name, value,
                    "Partial cover; some but not full protection.",
                    FactorDirection.Context,
                )
            }
        }

        "Soil Erosion" -> PriorityFactor(
            name, value,
            if (value.equals("Severe", ignoreCase = true)) {
                "Severe erosion observed directly at this point by a field worker."
            } else {
                "Erosion reported at this point by a field worker."
            },
            FactorDirection.Raises,
        )

        "Existing Structure" -> if (value.contains("none", ignoreCase = true) || value.contains("no ", ignoreCase = true)) {
            PriorityFactor(name, value, "No existing structure is protecting this site.", FactorDirection.Raises)
        } else {
            PriorityFactor(name, value, "A structure already exists here.", FactorDirection.Lowers)
        }

        "Agricultural Land" -> PriorityFactor(
            name, value,
            "Cultivated land downstream of the problem raises what is at stake.",
            FactorDirection.Context,
        )

        else -> null
    }

    private fun insightFor(raising: Int, missing: Int, total: Int): String = when {
        missing == total ->
            "No site measurements are available yet, so no structure can be responsibly recommended. " +
                "A field survey is the correct next step."
        missing > total / 2 ->
            "More than half this site's indicators are missing. Treat any recommendation as provisional " +
                "and confirm on the ground before committing budget."
        raising >= 3 ->
            "Several independent factors point the same way, which is the strongest signal this app can " +
                "produce from measurements alone. Prioritise a site visit."
        raising > 0 ->
            "There is a measured basis for acting here, but it rests on few factors. A field visit should " +
                "confirm severity before a structure is chosen."
        else ->
            "Nothing measured here raises priority. Any action should be justified by the field observation " +
                "rather than by site measurements."
    }

    /** Pulls the first number out of a display string like "14.2%", "82 m", "0.21 sparse". */
    private fun firstNumber(text: String): Double? =
        Regex("-?\\d+(\\.\\d+)?").find(text)?.value?.toDoubleOrNull()
}
