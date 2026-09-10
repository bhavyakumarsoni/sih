package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.model.AiCertainty
import com.geowatershed.app.data.model.AiStatus
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * The experimental AI assist panel.
 *
 * Three things this card is built to never do:
 *  - present a suggestion as a finding (it is always labelled a suggestion,
 *    always experimental, and always carries a confirm/reject pair),
 *  - imply it changed the priority score (it says so explicitly),
 *  - imply a single photo characterises the surrounding area.
 *
 * GIS supplies the spatial evidence, AI assists interpretation, the field
 * user decides. This card is the third of those, made visible.
 */
@Composable
fun AiSuggestionCard(
    capture: CaptureEntity,
    isConfigured: Boolean,
    busy: Boolean,
    onQueue: () -> Unit,
    onConfirm: () -> Unit,
    onReject: () -> Unit,
    onRetry: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status = runCatching { AiStatus.valueOf(capture.aiStatus) }.getOrDefault(AiStatus.NotRequested)
    val suggestedLabel = capture.aiSuggestedType
        ?.let { raw -> ObservationType.entries.firstOrNull { it.name == raw }?.label ?: raw }
    val certainty = capture.aiCertainty?.let { AiCertainty.valueOf(it) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(16.dp))
            .dashedBorder(GWColors.DashedBorder, strokeWidth = 1.5.dp, cornerRadius = 16.dp)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SectionLabel("AI ASSIST")
            Text(
                "EXPERIMENTAL",
                style = GWType.labelSmall,
                color = GWColors.Ink500,
                modifier = Modifier
                    .dashedBorder(GWColors.DashedBorder, strokeWidth = 1.dp, cornerRadius = 6.dp)
                    .padding(horizontal = 8.dp, vertical = 5.dp),
            )
        }

        when (status) {
            AiStatus.NotRequested -> {
                if (capture.photoPath == null) {
                    Text(
                        "No photo was saved with this capture, so there is nothing to classify.",
                        style = GWType.bodySmall,
                        color = GWColors.Ink600,
                    )
                } else if (!isConfigured) {
                    Text(
                        "AI assist is switched off — no API key is set on this device.",
                        style = GWType.bodySmall,
                        color = GWColors.Ink600,
                    )
                    AiActionButton("Open AI Assist settings", onOpenSettings)
                } else {
                    Text(
                        "Ask the model to suggest an observation category from this photo. " +
                            "It is a suggestion for you to accept or reject, not a finding.",
                        style = GWType.bodySmall,
                        color = GWColors.Ink600,
                    )
                    AiActionButton(if (busy) "Working…" else "Suggest a category", onQueue, enabled = !busy)
                }
            }

            AiStatus.Queued -> Text(
                "Queued. This photo has not been sent anywhere yet — it will be classified " +
                    "when this device next has network.",
                style = GWType.bodySmall,
                color = GWColors.Ink600,
            )

            AiStatus.Running -> Text("Classifying…", style = GWType.bodySmall, color = GWColors.Ink600)

            AiStatus.Suggested -> {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        suggestedLabel ?: "Unknown",
                        style = GWType.listTitle,
                        color = GWColors.Ink900,
                    )
                    certainty?.let {
                        Text(
                            it.label,
                            style = GWType.labelSmall,
                            color = GWColors.Ink500,
                            modifier = Modifier
                                .dashedBorder(GWColors.DashedBorder, strokeWidth = 1.dp, cornerRadius = 6.dp)
                                .padding(horizontal = 8.dp, vertical = 5.dp),
                        )
                    }
                }
                capture.aiRationale?.let {
                    Text(it, style = GWType.bodySmall, color = GWColors.Ink700)
                }
                Text(
                    "This has not changed the priority score and has not created any intervention. " +
                        "It describes one photograph, not the surrounding area.",
                    style = GWType.bodySmall.copy(fontSize = 12.5.sp, lineHeight = 17.sp),
                    color = GWColors.Ink600,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GWColors.MutedRowBg, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AiDecisionButton(
                        label = "✓ Confirm",
                        textColor = Color.White,
                        background = GWColors.Green700,
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f),
                    )
                    AiDecisionButton(
                        label = "✕ Reject",
                        textColor = GWColors.Ink700,
                        background = GWColors.MutedRowBg,
                        onClick = onReject,
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            AiStatus.Failed -> {
                Text(
                    "Classification failed. The capture and its photo are untouched.",
                    style = GWType.bodySmall,
                    color = GWColors.Ink600,
                )
                AiActionButton("Re-queue", onRetry)
            }

            AiStatus.Confirmed -> Text(
                "You confirmed this as ${suggestedLabel ?: "the suggested category"}. " +
                    "The observation type was updated by your decision; the priority score was not.",
                style = GWType.bodySmall,
                color = GWColors.Green700,
            )

            AiStatus.Rejected -> Text(
                "You rejected the suggestion of ${suggestedLabel ?: "the model's category"}. " +
                    "It is kept for the record and has not been applied.",
                style = GWType.bodySmall,
                color = GWColors.Ink600,
            )
        }
    }
}

@Composable
private fun AiActionButton(label: String, onClick: () -> Unit, enabled: Boolean = true) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(GWColors.NeutralBg, RoundedCornerShape(12.dp))
            .border(1.5.dp, GWColors.NeutralBorder, RoundedCornerShape(12.dp))
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            label,
            style = GWType.buttonLabelMedium,
            color = if (enabled) GWColors.Ink700 else GWColors.Ink300,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun AiDecisionButton(
    label: String,
    textColor: Color,
    background: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(54.dp)
            .background(background, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = GWType.buttonLabelMedium, color = textColor, textAlign = TextAlign.Center)
    }
}
