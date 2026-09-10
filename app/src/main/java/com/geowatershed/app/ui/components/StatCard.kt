package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

data class StatCardData(
    val value: String,
    val label: String,
    val valueColor: Color = GWColors.Ink900,
    val accentBar: Color? = null,
    val showCheck: Boolean = false,
)

@Composable
fun StatCard(data: StatCardData, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(14.dp))
            .background(GWColors.NeutralSurface)
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(14.dp)),
    ) {
        if (data.accentBar != null) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(5.dp)
                    .background(data.accentBar),
            )
        }
        Column(
            modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 13.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (data.showCheck) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(data.value, style = GWType.statNumber, color = data.valueColor)
                    Text("✓", style = GWType.bodyBold.copy(fontSize = 17.sp), color = data.valueColor)
                }
            } else {
                Text(data.value, style = GWType.statNumber, color = data.valueColor)
            }
            Text(data.label, style = GWType.statLabel, color = GWColors.Ink600)
        }
    }
}
