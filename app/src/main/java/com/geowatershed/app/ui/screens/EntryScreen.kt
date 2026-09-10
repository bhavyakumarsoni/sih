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
import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.components.rememberPermissionState
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * The app's true start screen: an attention summary for the watershed, then
 * a choice of mode. Field Mode needs no login and works offline; Governance
 * Mode is gated behind an officer login (see [GovernanceLoginScreen]).
 */
@Composable
fun EntryScreen(
    viewModel: GeoWatershedViewModel,
    onChooseField: () -> Unit,
    onChooseGovernance: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val captures by viewModel.captures.collectAsState()
    val interventions by viewModel.interventions.collectAsState()

    val locationPermission = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(locationPermission.granted) {
        if (locationPermission.granted) {
            viewModel.resolveWatershedLocation()
        } else {
            locationPermission.request()
        }
    }

    val highPriority = captures.count { it.priorityScore >= 70 }
    val stalled = interventions.count {
        val stage = InterventionStage.valueOf(it.stage)
        stage.ordinal < InterventionStage.Completed.ordinal &&
            System.currentTimeMillis() - it.createdAt > 30L * 24 * 60 * 60 * 1000
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GWColors.NeutralBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp)
            .padding(top = 40.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            SectionLabel("GEOWATERSHED", color = GWColors.Green700)
            Text(viewModel.watershedName, style = GWType.headline, color = GWColors.Ink900)
            Text(viewModel.watershedSub, style = GWType.dataMono, color = GWColors.Ink500)
        }

        if (highPriority > 0 || stalled > 0) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GWColors.SeverityHigh.copy(alpha = 0.08f), RoundedCornerShape(14.dp))
                    .border(1.dp, GWColors.SeverityHigh.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SectionLabel("ATTENTION REQUIRED TODAY", color = GWColors.SeverityHigh)
                if (highPriority > 0) {
                    Text(
                        "$highPriority site${if (highPriority == 1) "" else "s"} ${if (highPriority == 1) "is" else "are"} HIGH priority — action overdue",
                        style = GWType.body,
                        color = GWColors.Ink900,
                    )
                }
                if (stalled > 0) {
                    Text(
                        "$stalled intervention${if (stalled == 1) "" else "s"} stalled over 30 days",
                        style = GWType.body,
                        color = GWColors.Ink900,
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SectionLabel("CHOOSE MODE")
            ModeCard(
                title = "Field Mode",
                subtitle = "फील्ड मोड · लॉगिन की जरूरत नहीं",
                badge = "NO LOGIN · WORKS OFFLINE",
                accent = GWColors.Clay600,
                onClick = onChooseField,
            )
            ModeCard(
                title = "Governance Mode",
                subtitle = "Priority map, pipeline, approvals",
                badge = "OFFICIAL LOGIN REQUIRED",
                accent = GWColors.Green900,
                onClick = onChooseGovernance,
            )
        }
    }
}

@Composable
private fun ModeCard(
    title: String,
    subtitle: String,
    badge: String,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(accent, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, style = GWType.title, color = Color.White)
            Text(subtitle, style = GWType.bodySmall, color = Color.White.copy(alpha = 0.82f))
            Text(badge, style = GWType.meta.copy(fontSize = 11.5.sp), color = Color.White.copy(alpha = 0.78f))
        }
        Text("→", style = GWType.buttonLabel, color = Color.White)
    }
}
