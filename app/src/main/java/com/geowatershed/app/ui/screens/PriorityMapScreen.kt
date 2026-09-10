package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.PriorityZone
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.PriorityMap
import com.geowatershed.app.ui.components.PriorityZoneLegend
import com.geowatershed.app.ui.components.toMapPin
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * The dedicated full-screen priority map: every geo-tagged site plotted and
 * colour-coded, filterable by zone via the legend.
 *
 * The map is deliberately NOT inside a scrolling container — an interactive
 * map and a vertical scroll fight over the same drag gesture, and the map
 * loses. It takes the free height instead, with the legend pinned below it.
 */
@Composable
fun PriorityMapScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    onOpenSite: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val captures by viewModel.captures.collectAsState()
    val interventions by viewModel.interventions.collectAsState()
    var selectedZone by remember { mutableStateOf<PriorityZone?>(null) }

    val allPins = remember(captures, interventions) {
        captures.mapNotNull { it.toMapPin(interventions) }
    }
    val counts = remember(allPins) { allPins.groupingBy { it.zone }.eachCount() }
    val visiblePins = remember(allPins, selectedZone) {
        val zone = selectedZone
        if (zone == null) allPins else allPins.filter { it.zone == zone }
    }
    val unplotted = captures.size - allPins.size

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(
            title = "Priority Map",
            subtitle = selectedZone?.let { "${visiblePins.size} ${it.label.lowercase()}" }
                ?: "${allPins.size} of ${captures.size} sites plotted",
            onBack = onBack,
        )

        Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp)) {
            PriorityMap(
                pins = visiblePins,
                modifier = Modifier.fillMaxSize(),
                height = null,
                interactive = true,
                onPinTap = onOpenSite,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp),
        ) {
            PriorityZoneLegend(
                counts = counts,
                selected = selectedZone,
                onSelect = { selectedZone = it },
            )
            Text(
                if (selectedZone == null) {
                    "Tap a zone to filter · tap a pin to open that site"
                } else {
                    "Filtered to ${selectedZone?.label} — tap it again to show all zones"
                },
                style = GWType.meta,
                color = GWColors.Ink500,
            )
            if (unplotted > 0) {
                Text(
                    "$unplotted capture${if (unplotted == 1) "" else "s"} not shown — no GPS fix was recorded. " +
                        "They are missing from the map, not absent from the watershed.",
                    style = GWType.bodySmall.copy(fontSize = 12.5.sp),
                    color = GWColors.Ink600,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GWColors.MutedRowBg, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                )
            }
        }
    }
}
