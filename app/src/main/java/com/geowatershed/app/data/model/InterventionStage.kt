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
}
