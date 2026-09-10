package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.PriorityExplainer
import com.geowatershed.app.data.SiteAnalysisCalculator
import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.formatCoords
import com.geowatershed.app.ui.components.AiSuggestionCard
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.DataCompletenessCard
import com.geowatershed.app.ui.components.PriorityScoreCard
import com.geowatershed.app.ui.components.RecommendedInterventionCard
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.components.SiteIndicatorsList
import com.geowatershed.app.ui.components.WhyFlaggedCard
import com.geowatershed.app.ui.theme.GWColors

@Composable
fun SiteAnalysisScreen(
    viewModel: GeoWatershedViewModel,
    captureId: Long,
    onBack: () -> Unit,
    onInterventionCreated: () -> Unit,
    onOpenAiSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Observed rather than fetched once: an AI suggestion can land while this
    // screen is open, and the card has to reflect that without a re-navigation.
    val capture by viewModel.captureFlow(captureId).collectAsState(initial = null)

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        val currentCapture = capture
        BackHeader(
            title = "Site Analysis",
            subtitle = if (currentCapture != null) {
                "${currentCapture.siteCode} · ${formatCoords(currentCapture.latitude, currentCapture.longitude, currentCapture.accuracyMeters)}"
            } else {
                "loading…"
            },
            onBack = onBack,
        )
        if (currentCapture == null) {
            Text("Loading…", color = GWColors.Ink600, modifier = Modifier.padding(24.dp))
            return@Column
        }

        val indicators = remember(currentCapture) { SiteAnalysisCalculator.indicatorsFor(currentCapture) }
        val available = indicators.count { it.isAvailable }
        val explanation = remember(currentCapture, indicators) {
            PriorityExplainer.explain(currentCapture, indicators)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(top = 18.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            PriorityScoreCard(score = currentCapture.priorityScore)
            WhyFlaggedCard(explanation = explanation)
            DataCompletenessCard(available = available, total = indicators.size)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("SITE INDICATORS")
                SiteIndicatorsList(indicators = indicators)
            }
            AiSuggestionCard(
                capture = currentCapture,
                isConfigured = viewModel.aiSettings.isConfigured,
                busy = viewModel.aiBusy,
                onQueue = { viewModel.queueForAi(currentCapture) },
                onConfirm = { viewModel.confirmAiSuggestion(currentCapture) },
                onReject = { viewModel.rejectAiSuggestion(currentCapture) },
                onRetry = { viewModel.retryAi(currentCapture) },
                onOpenSettings = onOpenAiSettings,
            )
            val isSeedDemo = currentCapture.siteCode == SiteAnalysisCalculator.SEED_SITE_CODE
            val recommendedType = recommendedInterventionFor(currentCapture)
            RecommendedInterventionCard(
                type = recommendedType,
                reasoning = explanation.insight,
                costLabel = if (isSeedDemo) "EST. ₹48,000" else "COST · PENDING SURVEY",
                eligibilityLabel = if (isSeedDemo) "MGNREGA ELIGIBLE" else "ELIGIBILITY · TBD",
                ctaLabel = "Propose Intervention",
                onCreate = {
                    viewModel.createIntervention(recommendedType, currentCapture.id) {
                        onInterventionCreated()
                    }
                },
            )
        }
    }
}

/** A simple, honest suggestion until real intervention-design rules are wired up from the backend. */
internal fun recommendedInterventionFor(capture: CaptureEntity): String =
    if (capture.siteCode == SiteAnalysisCalculator.SEED_SITE_CODE) "Check Dam" else "Field Survey"
