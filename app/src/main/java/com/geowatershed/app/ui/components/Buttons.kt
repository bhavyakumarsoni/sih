package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * The big icon + title + subtitle action row used for the dashboard's two
 * primary actions ("Capture Field Image", "View Interventions").
 */
@Composable
fun BigActionButton(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .interactivePress(onClick = onClick, onClickLabel = title)
            .fillMaxWidth()
            .height(68.dp)
            .then(
                if (filled) {
                    Modifier.background(GWColors.Clay600, RoundedCornerShape(14.dp))
                } else {
                    Modifier
                        .background(GWColors.NeutralBg, RoundedCornerShape(14.dp))
                        .border(2.dp, GWColors.Green700, RoundedCornerShape(14.dp))
                }
            )
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        icon()
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(title, style = GWType.buttonLabel, color = if (filled) Color.White else GWColors.Green700)
            Text(
                subtitle,
                style = GWType.meta,
                color = if (filled) Color.White.copy(alpha = 0.78f) else GWColors.Ink600,
            )
        }
    }
}

/** Centered filled CTA, e.g. "Save & Analyze", "Create Intervention". */
@Composable
fun PrimaryCtaButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = GWColors.Clay600,
    height: androidx.compose.ui.unit.Dp = 68.dp,
    cornerRadius: androidx.compose.ui.unit.Dp = 14.dp,
    showArrow: Boolean = false,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .interactivePress(onClick = onClick, enabled = enabled, onClickLabel = label)
            .fillMaxWidth()
            .height(height)
            .background(if (enabled) background else GWColors.ProgressTrack, RoundedCornerShape(cornerRadius))
            .then(if (enabled) Modifier else Modifier.border(1.dp, GWColors.DividerHairline3, RoundedCornerShape(cornerRadius))),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            label,
            style = GWType.buttonLabel.copy(fontSize = 18.sp),
            color = if (enabled) Color.White else GWColors.Ink300,
        )
        if (showArrow && enabled) {
            Text("→", style = GWType.buttonLabel, color = Color.White.copy(alpha = 0.8f))
        }
    }
}
