package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.formatCoords
import com.geowatershed.app.data.model.InterventionStage
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

@Composable
fun InterventionCard(
    intervention: InterventionEntity,
    onAdvance: () -> Unit,
    onMonitoring: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val stage = InterventionStage.valueOf(intervention.stage)
    val isLast = stage.next == null
    val coordsText = formatCoords(intervention.latitude, intervention.longitude, intervention.accuracyMeters)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(intervention.type, style = GWType.listTitle, color = GWColors.Ink900)
                Text(intervention.siteDetail, style = GWType.dataMono.copy(fontSize = 12.sp, lineHeight = 12.sp), color = GWColors.Ink500)
            }
            SeverityPill(stage.label.uppercase(), stage.color)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Row(
                modifier = Modifier.size(20.dp).border(2.5.dp, GWColors.Ink500, CircleShape),
                horizontalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(5.dp)
                        .background(GWColors.Ink500, CircleShape),
                )
            }
            Text(coordsText, style = GWType.dataMono.copy(fontSize = 13.sp, lineHeight = 17.5.sp), color = GWColors.Ink600)
        }
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            val stages = InterventionStage.entries
            ProgressTrack(
                fraction = (stage.ordinal + 1) / stages.size.toFloat(),
                fillColor = stage.color,
                trackHeight = 8.dp,
            )
            val stepText = if (isLast) {
                "STEP ${stage.ordinal + 1} OF ${stages.size} · PIPELINE COMPLETE"
            } else {
                "STEP ${stage.ordinal + 1} OF ${stages.size} · NEXT: ${stage.next!!.label.uppercase()}"
            }
            Text(stepText, style = GWType.labelSmall.copy(fontSize = 11.sp, letterSpacing = 0.06.em), color = GWColors.Ink500)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(
                        if (isLast) GWColors.MutedRowBg else GWColors.NeutralBg,
                        RoundedCornerShape(12.dp),
                    )
                    .border(
                        if (isLast) 1.5.dp else 2.dp,
                        if (isLast) GWColors.DividerHairline2 else GWColors.Green700,
                        RoundedCornerShape(12.dp),
                    )
                    .clickable(enabled = !isLast, onClick = onAdvance),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    if (isLast) "Pipeline complete" else "Advance Status",
                    style = GWType.buttonLabelMedium.copy(fontWeight = if (isLast) FontWeight.Medium else FontWeight.SemiBold),
                    color = if (isLast) GWColors.Ink300 else GWColors.Green700,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(GWColors.Clay100, RoundedCornerShape(12.dp))
                    .border(1.5.dp, GWColors.ClayBorder, RoundedCornerShape(12.dp))
                    .clickable(onClick = onMonitoring),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Monitoring", style = GWType.buttonLabelMedium, color = GWColors.ClayTextDark2, textAlign = TextAlign.Center)
            }
        }
    }
}
