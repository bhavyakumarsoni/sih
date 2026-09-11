package com.geowatershed.app.ui.components

import android.provider.Settings
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import com.geowatershed.app.ui.theme.GWColors
import kotlin.math.min

private const val TOTAL_MS = 1200

/**
 * The launch sequence: a water drop settles, then turns over and becomes a
 * location pin, establishing the app's subject — water, and where it is.
 *
 * The morph is the whole trick and it is nearly free: a water drop and a map
 * pin are the same teardrop, pointing opposite ways. So this rotates one
 * shape 180 degrees and opens a hole in it rather than interpolating between
 * two different paths.
 *
 * This draws OVER the app rather than sitting in the navigation graph. A
 * splash that is a nav destination can be reached again with the back button
 * and complicates every back-stack decision after it; an overlay that fades
 * out cannot. If it ever misbehaves it fails on top of a working app.
 */
@Composable
fun LaunchSequence(onFinished: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val progress = remember { Animatable(0f) }

    // Respect the system animation setting. With animations off, this is a
    // 1.2-second delay that helps nobody.
    val animationsOff = remember {
        runCatching {
            Settings.Global.getFloat(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f
        }.getOrDefault(false)
    }

    LaunchedEffect(Unit) {
        if (animationsOff) {
            onFinished()
            return@LaunchedEffect
        }
        progress.animateTo(1f, tween(durationMillis = TOTAL_MS, easing = LinearEasing))
        onFinished()
    }

    if (animationsOff) return

    val t = progress.value
    // 0.00-0.50  the drop settles in
    // 0.50-0.83  it turns over and opens into a pin
    // 0.83-1.00  the whole overlay fades, revealing the app beneath
    val settle = (t / 0.50f).coerceIn(0f, 1f)
    val morph = ((t - 0.50f) / 0.33f).coerceIn(0f, 1f)
    val fade = ((t - 0.83f) / 0.17f).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(1f - fade)
            .background(GWColors.NeutralBg),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cx = size.width / 2f
            val cy = size.height / 2f
            val unit = min(size.width, size.height)

            // Drop falls in and overshoots slightly before settling.
            val entry = 1f - (1f - settle) * (1f - settle)
            val bodyRadius = unit * 0.085f * (0.55f + 0.45f * entry) * (1f - morph * 0.18f)
            val tipLength = bodyRadius * (2.15f - morph * 0.35f)

            rotate(degrees = 180f * morph, pivot = Offset(cx, cy)) {
                drawPath(
                    path = teardrop(cx, cy, bodyRadius, tipLength),
                    color = GWColors.Green700,
                )
                if (morph > 0f) {
                    // The pin's hole opens as the shape turns over.
                    drawCircle(
                        color = GWColors.NeutralBg,
                        radius = bodyRadius * 0.42f * morph,
                        center = Offset(cx, cy),
                    )
                    drawCircle(
                        color = GWColors.Clay600,
                        radius = bodyRadius * 0.24f * morph,
                        center = Offset(cx, cy),
                    )
                }
            }
        }
    }
}

/**
 * A teardrop centred on [cx],[cy]: a circle of [radius] with a point rising
 * [tip] above it. Rotated 180 degrees this is a map pin.
 */
private fun teardrop(cx: Float, cy: Float, radius: Float, tip: Float): Path = Path().apply {
    moveTo(cx, cy - tip)
    cubicTo(
        cx + radius * 0.85f, cy - tip * 0.42f,
        cx + radius, cy - radius * 0.30f,
        cx + radius, cy,
    )
    arcTo(
        rect = Rect(cx - radius, cy - radius, cx + radius, cy + radius),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 180f,
        forceMoveTo = false,
    )
    cubicTo(
        cx - radius, cy - radius * 0.30f,
        cx - radius * 0.85f, cy - tip * 0.42f,
        cx, cy - tip,
    )
    close()
}
