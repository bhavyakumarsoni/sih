package com.geowatershed.app.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Press physics for every button in the app: a spring compression to 95% on
 * touch, a light haptic, and an organic settle back to 100% on release.
 *
 * It also handles the blocked case. When [isAuthorized] is false the press
 * does NOT run [onClick] — it fires a heavier haptic and shakes the control
 * horizontally, so a field user who taps an officer-only action gets an
 * immediate physical answer instead of silence.
 *
 * Two deliberate choices worth knowing about:
 *
 * 1. A blocked control keeps its permanent "Officer approval required" label.
 *    The shake CONFIRMS a restriction the user could already read; it does
 *    not reveal one that was hidden until they tapped. A button that looks
 *    available until you try it is the same broken trust in a nicer coat.
 *
 * 2. The blocked state is NOT drawn in the dashed treatment. Dashed means
 *    "we have no data for this" throughout this app, and permission-denied is
 *    a different claim — the data exists, you are not the one who may act on
 *    it. Overloading the one honest signal would cost more than it buys.
 *
 * Placement matters: apply this FIRST in a modifier chain, before any
 * `background`/`border`, or the scale will not include them — it works
 * through a graphics layer, which only affects drawing that comes after it.
 */
fun Modifier.interactivePress(
    onClick: () -> Unit,
    enabled: Boolean = true,
    isAuthorized: Boolean = true,
    onBlockedTap: () -> Unit = {},
    onClickLabel: String? = null,
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val haptic = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) PRESSED_SCALE else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "press_scale",
    )
    val shake = remember { Animatable(0f) }

    this
        .scale(scale)
        .offset { IntOffset(shake.value.roundToInt(), 0) }
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = Role.Button,
        ) {
            if (isAuthorized) {
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onClick()
            } else {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onBlockedTap()
                scope.launch {
                    // Each step targets a different value so the animation is
                    // guaranteed to run; animating 0 -> 0 can be skipped.
                    val step = spring<Float>(stiffness = Spring.StiffnessHigh)
                    shake.animateTo(14f, step)
                    shake.animateTo(-11f, step)
                    shake.animateTo(7f, step)
                    shake.animateTo(0f, step)
                }
            }
        }
}

private const val PRESSED_SCALE = 0.95f
