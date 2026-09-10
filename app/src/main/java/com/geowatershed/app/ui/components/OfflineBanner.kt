package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.PlexMono

@Composable
fun OfflineQueueBanner(count: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.Clay100, RoundedCornerShape(12.dp))
            .border(1.dp, GWColors.ClayBorder, RoundedCornerShape(12.dp))
            .padding(horizontal = 15.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.size(22.dp).border(2.5.dp, GWColors.Clay600, CircleShape),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("↑", color = GWColors.Clay600, fontSize = 13.sp, modifier = Modifier.align(Alignment.CenterVertically))
        }
        Text(
            "$count record${if (count == 1) "" else "s"} queued offline\nwill sync when network returns",
            style = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Medium, fontSize = 13.sp, lineHeight = 17.5.sp),
            color = GWColors.ClayTextDark,
        )
    }
}
