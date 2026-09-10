package com.geowatershed.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

/**
 * GeoWatershed is a single-look field tool for outdoor use, not a day/night
 * consumer app — the palette below is intentionally the only theme. A dark
 * system setting does not change it.
 */
private val GWColorScheme = lightColorScheme(
    primary = GWColors.Green700,
    onPrimary = GWColors.NeutralSurface,
    secondary = GWColors.Clay600,
    onSecondary = GWColors.NeutralSurface,
    background = GWColors.NeutralBg,
    onBackground = GWColors.Ink900,
    surface = GWColors.NeutralSurface,
    onSurface = GWColors.Ink900,
    error = GWColors.SeverityHigh,
    onError = GWColors.NeutralSurface,
    outline = GWColors.NeutralBorder,
)

private val GWTypography = Typography(
    bodyLarge = GWType.body,
    bodyMedium = GWType.bodySmall,
    titleLarge = GWType.headline,
    titleMedium = GWType.title,
    labelLarge = GWType.buttonLabel.copy(fontWeight = FontWeight.SemiBold),
    labelSmall = GWType.label,
)

@Composable
fun GeoWatershedTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GWColorScheme,
        typography = GWTypography,
        content = content,
    )
}
