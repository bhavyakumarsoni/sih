package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/** Which surface the user is currently in. */
enum class AppMode(val label: String, val accent: Color) {
    Field("FIELD MODE", GWColors.Clay600),
    Governance("GOVERNANCE MODE", GWColors.Green900),
}

/**
 * A persistent strip pinned below every screen, carrying the two facts a
 * field user must never have to guess: which mode they are in, and whether
 * their work is actually saved.
 *
 * The storage wording is deliberately literal. There is no server to sync
 * to, so this does not promise a sync that cannot happen — it states what is
 * true, which is that the records are on this device.
 */
@Composable
fun ModeStatusBar(
    mode: AppMode,
    recordsOnDevice: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.Green900)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Column(modifier = Modifier.size(9.dp).background(mode.accent, CircleShape)) {}
            Text(mode.label, style = GWType.label, color = Color.White)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.10f), RoundedCornerShape(7.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            Text("✓", style = GWType.metaSmall, color = GWColors.GpsOnDot)
            Text(
                if (recordsOnDevice == 1) "1 RECORD SAVED ON DEVICE" else "$recordsOnDevice RECORDS SAVED ON DEVICE",
                style = GWType.metaSmall.copy(fontSize = 10.sp),
                color = GWColors.GreenSubtleOnDark,
            )
        }
    }
}
