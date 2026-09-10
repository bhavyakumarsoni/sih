package com.geowatershed.app.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.R

val PlexSans = FontFamily(
    Font(R.font.ibm_plex_sans_regular, FontWeight.Normal),
    Font(R.font.ibm_plex_sans_medium, FontWeight.Medium),
    Font(R.font.ibm_plex_sans_semibold, FontWeight.SemiBold),
    Font(R.font.ibm_plex_sans_bold, FontWeight.Bold),
)

val PlexMono = FontFamily(
    Font(R.font.ibm_plex_mono_regular, FontWeight.Normal),
    Font(R.font.ibm_plex_mono_medium, FontWeight.Medium),
    Font(R.font.ibm_plex_mono_semibold, FontWeight.SemiBold),
)

/**
 * Outdoor-first type scale from the handoff spec: body text never below
 * 16sp, section labels in Mono 600 with +0.13em tracking.
 */
object GWType {
    val statNumber = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.SemiBold, fontSize = 39.sp, lineHeight = 39.sp)
    val scoreNumber = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.SemiBold, fontSize = 62.sp, lineHeight = 62.sp, letterSpacing = (-0.02).em)
    val scoreSuffix = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Medium, fontSize = 20.sp, lineHeight = 20.sp)

    val headline = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 25.sp, lineHeight = 29.sp, letterSpacing = (-0.01).em)
    val title = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 22.sp)
    val cardTitle = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 26.sp, lineHeight = 29.sp)
    val listTitle = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 21.sp, lineHeight = 24.sp)

    val body = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 23.sp)
    val bodyBold = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 16.5.sp, lineHeight = 20.sp)
    val bodySmall = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.Normal, fontSize = 14.5.sp, lineHeight = 21.sp)

    val buttonLabel = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 17.5.sp, lineHeight = 17.5.sp)
    val buttonLabelMedium = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, lineHeight = 15.sp)
    val buttonLabelDash = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.SemiBold, fontSize = 14.5.sp, lineHeight = 14.5.sp)

    val dataMono = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 16.sp)
    val dataMonoMedium = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Medium, fontSize = 13.5.sp, lineHeight = 16.sp, letterSpacing = (-0.01).em)

    val label = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, lineHeight = 11.sp, letterSpacing = 0.13.em)
    val labelTrailing = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 11.sp)
    val labelSmall = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.SemiBold, fontSize = 10.5.sp, lineHeight = 10.5.sp, letterSpacing = 0.07.em)
    val statLabel = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Medium, fontSize = 11.5.sp, lineHeight = 14.4.sp, letterSpacing = 0.07.em)
    val meta = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Normal, fontSize = 11.5.sp, lineHeight = 14.sp)
    val metaSmall = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.Normal, fontSize = 10.5.sp, lineHeight = 12.sp)
    val pill = TextStyle(fontFamily = PlexSans, fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = 12.sp, letterSpacing = 0.09.em)
    val badge = TextStyle(fontFamily = PlexMono, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp, lineHeight = 12.5.sp)
}
