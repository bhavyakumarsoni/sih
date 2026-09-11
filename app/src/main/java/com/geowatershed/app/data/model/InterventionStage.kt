package com.geowatershed.app.data.model

import androidx.compose.ui.graphics.Color
import com.geowatershed.app.ui.theme.GWColors

enum class InterventionStage(val label: String, val color: Color) {
    Proposed("Proposed", GWColors.Ink500),
    Approved("Approved", GWColors.StageApproved),
    UnderConstruction("Under Construction", GWColors.SeverityMed),
    Completed("Completed", GWColors.Green700),
    Monitoring("Monitoring", GWColors.Clay600);

    val next: InterventionStage? get() = entries.getOrNull(ordinal + 1)

    /**
     * Whether the physical work is finished. [Monitoring] counts: it is a
     * post-completion check-in, not work still in progress.
     *
     * This exists because the Field dashboard and the Governance dashboard
     * were each deciding this for themselves and reaching DIFFERENT answers
     * for the same record — Field counted `ordinal >= Completed`, Governance
     * compared `== Completed`. Same database, same instant, two numbers on
     * screen. One definition, in one place, or it drifts again.
     *
     * Note this is NOT what the per-stage pipeline counts use: those ask
     * "how many are AT this stage", which is a different question.
     */
    val isDone: Boolean get() = ordinal >= Completed.ordinal

    companion object {
        /**
         * Reads a stage from a stored string without throwing.
         *
         * An unreadable value falls back to [Proposed] — the LEAST advanced
         * stage — deliberately. A corrupt or unknown record must never make
         * work look further along than it is: overstating progress is exactly
         * the failure this project refuses everywhere else, and "Completed"
         * is what triggers verification and payment downstream.
         */
        fun parse(raw: String?): InterventionStage =
            entries.firstOrNull { it.name == raw } ?: Proposed
    }
}
