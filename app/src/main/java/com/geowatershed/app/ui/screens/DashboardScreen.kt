package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.components.BigActionButton
import com.geowatershed.app.ui.components.DashboardHeader
import com.geowatershed.app.ui.components.OfflineQueueBanner
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.components.StatCard
import com.geowatershed.app.ui.components.StatCardData
import com.geowatershed.app.ui.theme.GWColors

@Composable
fun DashboardScreen(
    viewModel: GeoWatershedViewModel,
    onCaptureImage: () -> Unit,
    onViewInterventions: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val captures by viewModel.captures.collectAsState()
    val interventions by viewModel.interventions.collectAsState()
    val highPrioritySites = captures.count { it.priorityScore >= 70 }
    val interventionsCompleted = interventions.count {
        InterventionStage.valueOf(it.stage).ordinal >= InterventionStage.Completed.ordinal
    }

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        DashboardHeader(
            watershedName = viewModel.watershedName,
            watershedSub = viewModel.watershedSub,
            gpsOn = true,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
                .padding(top = 18.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("THIS WATERSHED")
                val stats = listOf(
                    StatCardData(value = "${captures.size}", label = "IMAGES\nCAPTURED"),
                    StatCardData(
                        value = "$highPrioritySites",
                        label = "HIGH PRIORITY\nSITES",
                        valueColor = GWColors.SeverityHigh,
                        accentBar = GWColors.SeverityHigh,
                    ),
                    StatCardData(value = "${interventions.size}", label = "INTERVENTIONS\nPROPOSED"),
                    StatCardData(
                        value = "$interventionsCompleted",
                        label = "INTERVENTIONS\nCOMPLETED",
                        valueColor = GWColors.Green700,
                        showCheck = true,
                    ),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(stats[0], modifier = Modifier.weight(1f))
                    StatCard(stats[1], modifier = Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(stats[2], modifier = Modifier.weight(1f))
                    StatCard(stats[3], modifier = Modifier.weight(1f))
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("PRIMARY ACTIONS")
                BigActionButton(
                    title = "Capture Field Image",
                    subtitle = "geo-tag + observation",
                    onClick = onCaptureImage,
                    filled = true,
                    icon = { CameraGlyph() },
                )
                BigActionButton(
                    title = "View Interventions",
                    subtitle = "${interventions.size} active records",
                    onClick = onViewInterventions,
                    filled = false,
                    icon = { ListGlyph() },
                )
            }
            OfflineQueueBanner(count = captures.size)
        }
    }
}

@Composable
private fun CameraGlyph() {
    Row(
        modifier = Modifier
            .size(width = 28.dp, height = 22.dp)
            .border(2.5.dp, Color.White, RoundedCornerShape(5.dp)),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(9.dp)
                .border(2.5.dp, Color.White, CircleShape),
        ) {}
    }
}

@Composable
private fun ListGlyph() {
    Row(
        modifier = Modifier
            .size(26.dp)
            .border(2.5.dp, GWColors.Green700, RoundedCornerShape(6.dp)),
        horizontalArrangement = Arrangement.Center,
    ) {
        androidx.compose.material3.Text(
            "≡",
            color = GWColors.Green700,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}
