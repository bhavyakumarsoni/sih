package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.InterventionCard
import com.geowatershed.app.ui.components.ProgressTrack
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

private const val STALLED_MS = 30L * 24 * 60 * 60 * 1000

private fun InterventionEntity.isStalled(): Boolean {
    val stage = InterventionStage.valueOf(stage)
    return stage.ordinal < InterventionStage.Completed.ordinal &&
        System.currentTimeMillis() - createdAt > STALLED_MS
}

/**
 * Governance Mode's pipeline view: same interventions as Field Mode's list,
 * reordered with stalled work (no stage change in 30+ days) surfaced first
 * and flagged red, plus a completion-rate summary.
 */
@Composable
fun GovernancePipelineScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    onOpenMonitoring: (InterventionEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interventions by viewModel.interventions.collectAsState()
    val completed = interventions.count { InterventionStage.valueOf(it.stage) == InterventionStage.Completed }
    val pending = interventions.size - completed
    val completionFraction = if (interventions.isEmpty()) 0f else completed / interventions.size.toFloat()
    val sorted = rememberSortedInterventions(interventions)

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(
            title = "Intervention Pipeline",
            subtitle = "${interventions.size} works",
            onBack = onBack,
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SectionLabel("WORK COMPLETED")
                Text("${(completionFraction * 100).toInt()}%", style = GWType.dataMonoMedium, color = GWColors.Green700)
            }
            ProgressTrack(fraction = completionFraction, fillColor = GWColors.Green700)
            Text("$completed done · $pending pending", style = GWType.meta, color = GWColors.Ink500)
        }
        if (sorted.isEmpty()) {
            Text("No interventions yet.", color = GWColors.Ink600, modifier = Modifier.padding(24.dp))
        }
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(sorted, key = { it.id }) { iv ->
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (iv.isStalled()) {
                        Text(
                            "STALLED — NO PROGRESS IN 30+ DAYS",
                            style = GWType.labelSmall,
                            color = GWColors.SeverityHigh,
                            modifier = Modifier
                                .background(GWColors.SeverityHigh.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                        )
                    }
                    InterventionCard(
                        intervention = iv,
                        onAdvance = { viewModel.advanceIntervention(iv) },
                        onMonitoring = { onOpenMonitoring(iv) },
                    )
                }
            }
        }
    }
}

@Composable
private fun rememberSortedInterventions(interventions: List<InterventionEntity>): List<InterventionEntity> =
    androidx.compose.runtime.remember(interventions) {
        interventions.sortedByDescending { it.isStalled() }
    }
