package com.geowatershed.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * GeoWatershed design tokens — deep-green + clay palette on a warm neutral
 * background. Names and hex values mirror the "GEOWATERSHED · DESIGN SYSTEM v1"
 * handoff spec (color tokens table).
 */
object GWColors {
    // Core tokens (handoff spec)
    val Green900 = Color(0xFF14342A) // app bar, headers
    val Green700 = Color(0xFF1F5C46) // primary, confirm, checkmarks
    val Green100 = Color(0xFFE9EFE9) // success surface
    val Clay600 = Color(0xFFA9502B) // field action CTA
    val Clay100 = Color(0xFFF3E6D8) // clay surface
    val SeverityHigh = Color(0xFFA9402A)
    val SeverityMed = Color(0xFFB9822B)
    val NeutralBg = Color(0xFFF4F1EA) // screen background
    val NeutralSurface = Color(0xFFFFFFFF) // cards
    val NeutralBorder = Color(0xFFD8D2C4) // hairlines, 1dp
    val Ink900 = Color(0xFF16201A) // primary text
    val Ink500 = Color(0xFF7B807A) // labels, unavailable
    val Ink300 = Color(0xFFA5A9A2) // counters, scale ticks, disabled labels

    // Supporting tones used across screens, not in the swatch grid but present
    // in the prototype styling.
    val GreenTextOnDark = Color(0xFF8FBBA6) // "GEOWATERSHED" wordmark on app bar
    val GreenSubtleOnDark = Color(0xFFA8C7B7) // header subtitle on app bar
    val GpsOnDot = Color(0xFF79C79B)
    val Ink700 = Color(0xFF3D4A41) // recommended-intervention body copy
    val Ink600 = Color(0xFF5A6159) // secondary body / meta text
    val Ink400 = Color(0xFF8A8F86) // tertiary meta text (dates, hints)
    val GreenBorder = Color(0xFFBFD3C4) // success-card border
    val ClayBorder = Color(0xFFE0C9B2) // clay-card border
    val ClayTextDark = Color(0xFF7A4423) // offline-queue banner text
    val ClayTextDark2 = Color(0xFF8F4523) // monitoring pill / secondary CTA text
    val DashedBorder = Color(0xFFB5B0A3) // unavailable dashed outline
    val DashedBorderStrong = Color(0xFFC2BCAC) // before-photo dashed outline
    val AfterDashedBorder = Color(0xFF9FB8A6)
    val AfterSurfaceTint = Color(0xFFEDF2ED)
    val SelectedRowBg = Color(0xFFF0F4F0) // selected observation-type row
    val MutedRowBg = Color(0xFFF1EEE5) // unavailable indicator row (muted-row treatment)
    val MutedChipBg = Color(0xFFE4E0D5)
    val ProgressTrack = Color(0xFFEAE5DA)
    val DividerHairline = Color(0xFFE9E5DB)
    val DividerHairline2 = Color(0xFFE4DFD3)
    val DividerHairline3 = Color(0xFFDED8CB)
    val AfterDividerHairline = Color(0xFFDDE6DC) // after-photo card footer divider
    val StageApproved = Color(0xFF4A7C63)

    // Camera viewfinder / photo placeholder tones
    val ViewfinderDark1 = Color(0xFF242A24)
    val BeforePlaceholder1 = Color(0xFFDAD4C6)
    val BeforePlaceholder2 = Color(0xFFE4DFD3)
    val AfterPlaceholder1 = Color(0xFFCBD9CC)
    val AfterPlaceholder2 = Color(0xFFD8E2D7)
    val AfterCaption = Color(0xFF5E7A66)
}
