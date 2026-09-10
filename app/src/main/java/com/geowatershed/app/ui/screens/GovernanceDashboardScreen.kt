package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.components.PriorityMap
import com.geowatershed.app.ui.components.PriorityZoneLegend
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.components.SeverityPill
import com.geowatershed.app.ui.components.PriorityLevel
import com.geowatershed.app.ui.components.priorityLevelFor
import com.geowatershed.app.ui.components.toMapPin
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * Governance Mode's home: a real, live map of every geo-tagged site (pins
 * plotted from Room capture records) plus a pipeline summary. This is the
 * screen the officer lands on right after logging in.
 */
@Composable
fun GovernanceDashboardScreen(
    viewModel: GeoWatershedViewModel,
    onOpenSite: (Long) -> Unit,
    onOpenPipeline: () -> Unit,
    onOpenMap: () -> Unit,
    onOpenAiSettings: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val captures by viewModel.captures.collectAsState()
    val interventions by viewModel.interventions.collectAsState()
    val highPriority = captures.count { it.priorityScore >= 70 }
    val stalled = interventions.count {
        val stage = InterventionStage.valueOf(it.stage)
        stage.ordinal < InterventionStage.Completed.ordinal &&
            System.currentTimeMillis() - it.createdAt > 30L * 24 * 60 * 60 * 1000
    }
    val completed = interventions.count { InterventionStage.valueOf(it.stage) == InterventionStage.Completed }
    val pending = interventions.size - completed
    val pins = captures.mapNotNull { it.toMapPin(interventions) }
    val zoneCounts = pins.groupingBy { it.zone }.eachCount()
    val byLevel = captures.groupingBy { priorityLevelFor(it.priorityScore) }.eachCount()

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GWColors.Green900)
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                SectionLabel("GOVERNANCE MODE", color = GWColors.GreenTextOnDark)
                Text(
                    "LOG OUT",
                    style = GWType.meta,
                    color = GWColors.GreenSubtleOnDark,
                    modifier = Modifier.clickable(onClick = onLogout),
                )
            }
            Text(
                "${viewModel.governanceOfficerName} · ${viewModel.watershedName} · ${captures.size} sites",
                style = GWType.title,
                color = androidx.compose.ui.graphics.Color.White,
            )
            Text(
                "$highPriority sites need action · $stalled stalled over 30 days",
                style = GWType.dataMono,
                color = GWColors.GreenSubtleOnDark,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
                .padding(top = 18.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("PRIORITY MAP")
                PriorityMap(
                    pins = pins,
                    height = 240.dp,
                    interactive = false,
                    onSurfaceTap = onOpenMap,
                )
                PriorityZoneLegend(counts = zoneCounts)
                Text(
                    "Live OpenStreetMap · tap the map to open the full priority map",
                    style = GWType.meta,
                    color = GWColors.Ink500,
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("SITES BY PRIORITY")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SeverityPill("${byLevel[PriorityLevel.High] ?: 0} HIGH", GWColors.SeverityHigh)
                    SeverityPill("${byLevel[PriorityLevel.Medium] ?: 0} MED", GWColors.SeverityMed)
                    SeverityPill("${byLevel[PriorityLevel.Low] ?: 0} LOW", GWColors.Green700)
                }
            }
            if (captures.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    SectionLabel("SITES")
                    captures.sortedByDescending { it.priorityScore }.forEach { capture ->
                        val level = priorityLevelFor(capture.priorityScore)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
                                .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
                                .clickable { onOpenSite(capture.id) }
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text(capture.siteCode, style = GWType.listTitle, color = GWColors.Ink900)
                                SeverityPill(level.label, level.color)
                            }
                            Text("Priority score ${capture.priorityScore}/100 · tap to open site analysis", style = GWType.meta, color = GWColors.Ink500)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
                    .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
                    .clickable(onClick = onOpenPipeline)
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    SectionLabel("INTERVENTION PIPELINE")
                    Text("$completed completed · $pending pending", style = GWType.body, color = GWColors.Ink900)
                }
                Text("OPEN →", style = GWType.buttonLabelMedium, color = GWColors.Green700)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
                    .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
                    .clickable(onClick = onOpenAiSettings)
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    SectionLabel("AI ASSIST · EXPERIMENTAL")
                    Text(
                        if (viewModel.aiSettings.isConfigured) "Configured on this device" else "Not configured",
                        style = GWType.body,
                        color = GWColors.Ink900,
                    )
                }
                Text("OPEN →", style = GWType.buttonLabelMedium, color = GWColors.Green700)
            }
        }
    }
}
