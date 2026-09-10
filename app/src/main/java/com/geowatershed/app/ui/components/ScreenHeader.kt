package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/** Dark app-bar header with a back chevron, used by every screen except the dashboard. */
@Composable
fun BackHeader(title: String, subtitle: String, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.Green900)
            .padding(start = 8.dp, end = 18.dp, top = 4.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", color = Color.White, fontSize = 26.sp)
        }
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(title, style = GWType.title, color = Color.White)
            Text(subtitle, style = GWType.meta, color = GWColors.GreenSubtleOnDark)
        }
    }
}

/** Dashboard's own header: wordmark + GPS indicator, watershed name and location line. */
@Composable
fun DashboardHeader(
    watershedName: String,
    watershedSub: String,
    gpsStatus: String,
    gpsActive: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.Green900)
            .padding(start = 18.dp, end = 18.dp, top = 6.dp, bottom = 18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("GEOWATERSHED", style = GWType.label, color = GWColors.GreenTextOnDark)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                Column(
                    modifier = Modifier
                        .size(8.dp)
                        .background(if (gpsActive) GWColors.GpsOnDot else GWColors.Ink500, CircleShape),
                ) {}
                Text(
                    gpsStatus,
                    style = GWType.dataMonoMedium.copy(fontSize = 11.sp),
                    color = GWColors.GreenTextOnDark,
                )
            }
        }
        Text(watershedName, style = GWType.headline, color = Color.White)
        Text(watershedSub, style = GWType.dataMono, color = GWColors.GreenSubtleOnDark)
    }
}
