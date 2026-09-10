package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.model.SiteIndicator
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

enum class PriorityLevel(val label: String, val color: Color) {
    High("HIGH", GWColors.SeverityHigh),
    Medium("MEDIUM", GWColors.SeverityMed),
    Low("LOW", GWColors.Green700),
}

fun priorityLevelFor(score: Int): PriorityLevel = when {
    score >= 70 -> PriorityLevel.High
    score >= 40 -> PriorityLevel.Medium
    else -> PriorityLevel.Low
}

@Composable
fun PriorityScoreCard(score: Int, modifier: Modifier = Modifier) {
    val level = priorityLevelFor(score)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                SectionLabel("PRIORITY SCORE")
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("$score", style = GWType.scoreNumber, color = level.color)
                    Text("/100", style = GWType.scoreSuffix, color = GWColors.Ink300)
                }
            }
            SeverityPill(level.label, level.color)
        }
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            ProgressTrack(fraction = score / 100f, fillColor = level.color)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                val tickStyle = GWType.labelSmall.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium, letterSpacing = 0.em)
                Text("0 LOW", style = tickStyle, color = GWColors.Ink300)
                Text("40 MED", style = tickStyle, color = GWColors.Ink300)
                Text("70 HIGH", style = tickStyle, color = GWColors.Ink300)
                Text("100", style = tickStyle, color = GWColors.Ink300)
            }
        }
    }
}

@Composable
fun DataCompletenessCard(available: Int, total: Int, modifier: Modifier = Modifier) {
    val missing = total - available
    val note = if (missing > 0) {
        "$missing indicator${if (missing == 1) "" else "s"} unavailable — they are excluded from the score, not counted as zero."
    } else {
        "All indicators available for this site."
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(16.dp))
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            SectionLabel("DATA COMPLETENESS")
            Text("$available of $total", style = GWType.dataMonoMedium.copy(fontSize = 13.sp), color = GWColors.Ink900)
        }
        ProgressTrack(fraction = available / total.toFloat(), fillColor = GWColors.Green700, trackHeight = 10.dp)
        Text(note, style = GWType.bodySmall.copy(fontSize = 12.5.sp, lineHeight = 17.sp), color = GWColors.Ink600)
    }
}

@Composable
fun SiteIndicatorsList(indicators: List<SiteIndicator>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(14.dp))
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(14.dp)),
    ) {
        indicators.forEachIndexed { index, indicator ->
            if (index > 0) HorizontalDivider(color = GWColors.DividerHairline, thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(13.dp),
            ) {
                if (indicator.isAvailable) {
                    Row(
                        modifier = Modifier.size(26.dp).background(GWColors.Green700, CircleShape),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text("✓", color = Color.White, fontSize = 13.sp, modifier = Modifier.align(Alignment.CenterVertically))
                    }
                } else {
                    Row(
                        modifier = Modifier.size(26.dp).dashedBorder(GWColors.DashedBorder, strokeWidth = 1.5.dp, cornerRadius = 13.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text("–", color = GWColors.Ink400, fontSize = 15.sp, modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        indicator.name,
                        style = GWType.bodyBold.copy(fontSize = 16.sp),
                        color = if (indicator.isAvailable) GWColors.Ink900 else GWColors.Ink600,
                    )
                    Text(indicator.sub, style = GWType.meta.copy(fontSize = 11.5.sp), color = GWColors.Ink400)
                }
                val value = indicator.value
                if (value != null) {
                    Text(
                        value,
                        style = GWType.dataMonoMedium.copy(fontSize = 15.5.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold, letterSpacing = 0.em),
                        color = GWColors.Ink900,
                    )
                } else {
                    Text(
                        "UNAVAILABLE",
                        style = GWType.labelSmall.copy(letterSpacing = 0.07.em),
                        color = GWColors.Ink500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .dashedBorder(GWColors.DashedBorder, strokeWidth = 1.5.dp, cornerRadius = 7.dp)
                            .padding(horizontal = 10.dp, vertical = 7.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendedInterventionCard(
    type: String,
    reasoning: String,
    costLabel: String,
    eligibilityLabel: String,
    ctaLabel: String,
    onCreate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.Green100, RoundedCornerShape(16.dp))
            .border(1.dp, GWColors.GreenBorder, RoundedCornerShape(16.dp))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.size(22.dp).background(GWColors.Green700, CircleShape),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text("✓", color = Color.White, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
            SectionLabel("RECOMMENDED INTERVENTION", color = GWColors.Green700)
        }
        Text(type, style = GWType.cardTitle, color = GWColors.Green900)
        Text(reasoning, style = GWType.bodySmall, color = GWColors.Ink700)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(costLabel, eligibilityLabel).forEach { tag ->
                Text(
                    tag,
                    style = GWType.dataMonoMedium.copy(fontSize = 11.5.sp, letterSpacing = 0.em),
                    color = GWColors.Green700,
                    modifier = Modifier
                        .background(GWColors.Green700.copy(alpha = 0.1f), RoundedCornerShape(7.dp))
                        .padding(horizontal = 11.dp, vertical = 7.dp),
                )
            }
        }
        PrimaryCtaButton(label = ctaLabel, onClick = onCreate, background = GWColors.Green700, height = 64.dp, cornerRadius = 13.dp)
    }
}
