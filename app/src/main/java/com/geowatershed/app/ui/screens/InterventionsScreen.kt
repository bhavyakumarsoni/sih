package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.InterventionCard
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.PlexMono

private enum class InterventionFilter(val label: String) {
    All("ALL"), Proposed("PROPOSED"), Active("ACTIVE"), Done("DONE");

    fun matches(stage: InterventionStage): Boolean = when (this) {
        All -> true
        Proposed -> stage == InterventionStage.Proposed
        Active -> stage == InterventionStage.Approved || stage == InterventionStage.UnderConstruction
        Done -> stage == InterventionStage.Completed || stage == InterventionStage.Monitoring
    }
}

@Composable
fun InterventionsScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    onOpenMonitoring: (InterventionEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interventions by viewModel.interventions.collectAsState()
    var filter by remember { mutableStateOf(InterventionFilter.All) }
    val visible = remember(interventions, filter) {
        interventions.filter { filter.matches(InterventionStage.valueOf(it.stage)) }
    }

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(
            title = "Interventions",
            subtitle = "${viewModel.watershedName.removeSuffix(" Watershed")} · ${interventions.size} records",
            onBack = onBack,
        )
        FilterChipRow(
            counts = InterventionFilter.entries.associateWith { f ->
                interventions.count { f.matches(InterventionStage.valueOf(it.stage)) }
            },
            selected = filter,
            onSelect = { filter = it },
        )
        if (visible.isEmpty()) {
            Text(
                "No interventions in this filter yet.",
                color = GWColors.Ink600,
                modifier = Modifier.padding(24.dp),
            )
        }
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(visible, key = { it.id }) { iv ->
                InterventionCard(
                    intervention = iv,
                    onAdvance = { viewModel.advanceIntervention(iv) },
                    onMonitoring = { onOpenMonitoring(iv) },
                )
            }
        }
    }
}

@Composable
private fun FilterChipRow(
    counts: Map<InterventionFilter, Int>,
    selected: InterventionFilter,
    onSelect: (InterventionFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().background(GWColors.NeutralBg)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(start = 18.dp, end = 18.dp, top = 14.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            InterventionFilter.entries.forEach { f ->
                FilterChip(
                    label = "${f.label} ${counts[f] ?: 0}",
                    active = f == selected,
                    onClick = { onSelect(f) },
                )
            }
        }
        HorizontalDivider(color = GWColors.DividerHairline3, thickness = 1.dp)
    }
}

private val filterChipTextStyle = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Medium, fontSize = 12.5.sp, lineHeight = 12.5.sp)

@Composable
private fun FilterChip(label: String, active: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Text(
        label,
        style = filterChipTextStyle,
        color = if (active) Color.White else GWColors.Ink600,
        modifier = modifier
            .background(if (active) GWColors.Green900 else GWColors.NeutralSurface, RoundedCornerShape(8.dp))
            .then(if (active) Modifier else Modifier.border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(8.dp)))
            .clickable(onClick = onClick)
            .padding(horizontal = 15.dp, vertical = 11.dp),
    )
}
