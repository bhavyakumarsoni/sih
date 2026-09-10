package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/** A rounded-rect border drawn as a dash pattern — Compose has no built-in dashed border. */
fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
    dashLength: Dp = 6.dp,
    gapLength: Dp = 5.dp,
): Modifier = drawBehind {
    val strokePx = strokeWidth.toPx()
    val inset = strokePx / 2
    drawRoundRect(
        color = color,
        topLeft = androidx.compose.ui.geometry.Offset(inset, inset),
        size = Size(size.width - strokePx, size.height - strokePx),
        cornerRadius = CornerRadius(cornerRadius.toPx() - inset, cornerRadius.toPx() - inset),
        style = Stroke(
            width = strokePx,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength.toPx(), gapLength.toPx()), 0f),
        ),
    )
}

/** Small-caps mono section label, e.g. "PRIORITY SCORE". */
@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier, color: Color = GWColors.Ink500) {
    Text(text, style = GWType.label, color = color, modifier = modifier)
}

/** A section label with an unrelated bit of context pinned to the far end, e.g. label + "REQUIRED". */
@Composable
fun SectionLabelRow(label: String, trailing: String, trailingColor: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        SectionLabel(label)
        Text(trailing, style = GWType.labelTrailing, color = trailingColor)
    }
}

/** A section label with a trailing suffix that reads as part of the same phrase, e.g. "DESCRIPTION · OPTIONAL". */
@Composable
fun SectionLabelWithSuffix(label: String, suffix: String, suffixColor: Color, modifier: Modifier = Modifier) {
    val text = buildAnnotatedString {
        append(label)
        append(' ')
        withStyle(SpanStyle(color = suffixColor)) { append(suffix) }
    }
    Text(text, style = GWType.label, color = GWColors.Ink500, modifier = modifier)
}

/** Severity / status pill, e.g. HIGH · MEDIUM · LOW · a pipeline stage name. */
@Composable
fun SeverityPill(text: String, background: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = GWType.pill,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(background, RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 9.dp),
    )
}

/** Flat progress track: a rounded background bar with a colored fill proportional to [fraction]. */
@Composable
fun ProgressTrack(
    fraction: Float,
    fillColor: Color,
    modifier: Modifier = Modifier,
    trackHeight: androidx.compose.ui.unit.Dp = 12.dp,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(trackHeight)
            .background(GWColors.ProgressTrack, RoundedCornerShape(trackHeight / 2)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(fraction.coerceIn(0f, 1f))
                .background(fillColor, RoundedCornerShape(trackHeight / 2)),
        ) {}
    }
}
