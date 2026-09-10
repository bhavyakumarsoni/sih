package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.PrimaryCtaButton
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * Where the operator supplies the AI credentials, on the device, at runtime.
 *
 * The key is stored in this app's private preferences and nowhere else. It is
 * not in the repository and not compiled into the APK, because a key baked
 * into an APK ships to every device that installs it and can be pulled back
 * out of the package by anyone who cares to.
 */
@Composable
fun AiSettingsScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val settings = viewModel.aiSettings
    var key by remember { mutableStateOf(settings.apiKey.orEmpty()) }
    var model by remember { mutableStateOf(settings.model) }
    var saved by remember { mutableStateOf(false) }
    val queued by viewModel.aiQueueCount.collectAsState()

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(
            title = "AI Assist",
            subtitle = if (settings.isConfigured) "configured on this device" else "not configured",
            onBack = onBack,
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(top = 20.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                "AI assist is experimental. It suggests an observation category from a captured " +
                    "photo, for a person to accept or reject. It never changes a priority score, " +
                    "never creates or advances an intervention, and never substitutes for the " +
                    "GIS evidence or the field worker's own judgement.",
                style = GWType.bodySmall,
                color = GWColors.Ink700,
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionLabel("API KEY")
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it; saved = false },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GWColors.Green700,
                        unfocusedBorderColor = GWColors.NeutralBorder,
                    ),
                )
                Text(
                    "Stored only in this app's private storage on this device.",
                    style = GWType.meta,
                    color = GWColors.Ink500,
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionLabel("MODEL")
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it; saved = false },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GWColors.Green700,
                        unfocusedBorderColor = GWColors.NeutralBorder,
                    ),
                )
                Text(
                    "Editable because model identifiers change. Check the current one before a demo.",
                    style = GWType.meta,
                    color = GWColors.Ink500,
                )
            }

            PrimaryCtaButton(
                label = if (saved) "✓ Saved" else "Save",
                onClick = {
                    settings.apiKey = key
                    settings.model = model
                    saved = true
                    viewModel.processAiQueue()
                },
                background = GWColors.Green700,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GWColors.MutedRowBg, RoundedCornerShape(12.dp))
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(9.dp),
            ) {
                SectionLabel("QUEUE")
                Text(
                    if (queued == 0) {
                        "Nothing waiting to be classified."
                    } else {
                        "$queued capture${if (queued == 1) "" else "s"} queued. They stay on this device " +
                            "until there is network — nothing has been sent yet."
                    },
                    style = GWType.bodySmall.copy(fontSize = 13.sp, lineHeight = 18.sp),
                    color = GWColors.Ink600,
                )
                if (queued > 0) {
                    PrimaryCtaButton(
                        label = if (viewModel.aiBusy) "Working…" else "Run queue now",
                        onClick = { viewModel.processAiQueue() },
                        background = GWColors.Clay600,
                        height = 54.dp,
                        enabled = !viewModel.aiBusy,
                    )
                }
            }

            viewModel.aiMessage?.let { message ->
                Text(
                    message,
                    style = GWType.bodySmall,
                    color = GWColors.Ink700,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GWColors.Clay100, RoundedCornerShape(10.dp))
                        .padding(12.dp),
                )
            }
        }
    }
}
