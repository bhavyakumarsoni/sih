package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GpsFix
import com.geowatershed.app.data.formatCoords
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType
import kotlin.math.roundToInt

private val gpsTitleStyle = GWType.label.copy(fontSize = 11.5.sp, letterSpacing = 0.11.em)
private val gpsCoordStyle = GWType.dataMonoMedium.copy(fontSize = 13.5.sp, letterSpacing = (-0.01).em)

/**
 * The GPS status card on the Capture screen, backed by a real one-shot
 * device location fix. Tapping it retries the fix.
 */
@Composable
fun GpsCard(fix: GpsFix?, searching: Boolean, permissionGranted: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val hasFix = fix != null
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .then(
                if (hasFix) {
                    Modifier
                        .background(GWColors.Green100, RoundedCornerShape(14.dp))
                        .border(1.dp, GWColors.GreenBorder, RoundedCornerShape(14.dp))
                } else {
                    Modifier
                        .background(GWColors.MutedRowBg, RoundedCornerShape(14.dp))
                        .dashedBorder(GWColors.DashedBorderStrong, strokeWidth = 1.5.dp, cornerRadius = 14.dp)
                },
            )
            .padding(horizontal = 16.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        val ringColor = if (hasFix) GWColors.Green700 else GWColors.Ink400
        Row(
            modifier = Modifier
                .size(34.dp)
                .border(3.dp, ringColor, CircleShape),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(9.dp)
                    .background(ringColor, CircleShape),
            ) {}
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            val title = when {
                !permissionGranted -> "LOCATION PERMISSION NEEDED"
                hasFix -> "GPS FIX ACQUIRED"
                searching -> "ACQUIRING FIX…"
                else -> "WAITING FOR FIX — TAP TO RETRY"
            }
            Text(title, style = gpsTitleStyle, color = if (hasFix) GWColors.Green700 else GWColors.Ink500)
            val coordText = when {
                !permissionGranted -> "tap to grant access"
                hasFix -> formatCoords(fix!!.latitude, fix.longitude, fix.accuracyMeters)
                searching -> "requesting device location…"
                else -> "no fix yet"
            }
            Text(
                coordText,
                style = gpsCoordStyle,
                color = if (hasFix) GWColors.Ink900 else GWColors.Ink600,
                maxLines = 1,
            )
        }
        val badgeText = if (hasFix) "±${fix!!.accuracyMeters.roundToInt()} m" else "···"
        Text(
            badgeText,
            style = GWType.badge,
            color = if (hasFix) Color.White else GWColors.Ink500,
            modifier = Modifier
                .background(if (hasFix) GWColors.Green700 else GWColors.MutedChipBg, RoundedCornerShape(8.dp))
                .padding(horizontal = 11.dp, vertical = 9.dp),
        )
    }
}
