package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.PrimaryCtaButton
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

/**
 * Governance Mode's only door in: an officer ID + password issued by the BDO
 * office. There is intentionally no "create account" / "sign up" affordance
 * anywhere on this screen — credentials are provisioned out of band.
 */
@Composable
fun GovernanceLoginScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    onLoggedIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var officerId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(title = "Governance Mode", subtitle = "Official login required", onBack = onBack)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                "Sign in with the officer credentials issued by your BDO office. Accounts are not self-service — contact your office if you need access or a reset.",
                style = GWType.bodySmall,
                color = GWColors.Ink600,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionLabel("OFFICER ID")
                OutlinedTextField(
                    value = officerId,
                    onValueChange = { officerId = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GWColors.Green700,
                        unfocusedBorderColor = GWColors.NeutralBorder,
                    ),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionLabel("PASSWORD")
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GWColors.Green700,
                        unfocusedBorderColor = GWColors.NeutralBorder,
                    ),
                )
            }
            if (viewModel.governanceLoginError != null) {
                Text(
                    viewModel.governanceLoginError!!,
                    style = GWType.bodySmall,
                    color = GWColors.SeverityHigh,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GWColors.SeverityHigh.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                        .padding(12.dp),
                )
            }
            PrimaryCtaButton(
                label = "Sign In",
                onClick = {
                    viewModel.attemptGovernanceLogin(officerId, password)
                    if (viewModel.isGovernanceAuthed) onLoggedIn()
                },
                background = GWColors.Green900,
            )
        }
    }
}
