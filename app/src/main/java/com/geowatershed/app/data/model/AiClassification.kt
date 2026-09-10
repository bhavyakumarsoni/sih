package com.geowatershed.app.data.model

/**
 * Lifecycle of an AI suggestion for one capture.
 *
 * Note there is no state in which an AI suggestion is simply "true". It is
 * Suggested until a human presses Confirm or Reject; nothing downstream reads
 * it as fact before that, and nothing reads it as a priority input ever.
 */
enum class AiStatus {
    /** No classification asked for (the default for every capture). */
    NotRequested,

    /** Waiting for network. Nothing has been sent anywhere yet. */
    Queued,

    /** A request is in flight. */
    Running,

    /** A suggestion came back and is awaiting the field user's judgement. */
    Suggested,

    /** The attempt failed. The capture is untouched and can be re-queued. */
    Failed,

    /** A human agreed with the suggestion. */
    Confirmed,

    /** A human disagreed. The suggestion is kept for audit, not applied. */
    Rejected,
}

/**
 * Self-reported confidence band. Deliberately three coarse buckets rather
 * than a percentage — a model's numeric confidence reads as precision it has
 * not earned, and this app does not manufacture precision anywhere else.
 */
enum class AiCertainty(val label: String) {
    High("HIGH CERTAINTY"),
    Medium("MEDIUM CERTAINTY"),
    Low("LOW CERTAINTY"),
    ;

    companion object {
        fun parse(raw: String?): AiCertainty = when (raw?.trim()?.lowercase()) {
            "high" -> High
            "medium" -> Medium
            else -> Low
        }
    }
}
