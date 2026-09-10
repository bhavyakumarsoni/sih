package com.geowatershed.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.PriorityExplainer
import com.geowatershed.app.data.SiteAnalysisCalculator
import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.formatCoords
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.DataCompletenessCard
import com.geowatershed.app.ui.components.PriorityScoreCard
import com.geowatershed.app.ui.components.RecommendedInterventionCard
import com.geowatershed.app.ui.components.SectionLabel
import com.geowatershed.app.ui.components.SeverityPill
import com.geowatershed.app.ui.components.SiteIndicatorsList
import com.geowatershed.app.ui.components.WhyFlaggedCard
import com.geowatershed.app.ui.components.priorityLevelFor
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType
import java.util.concurrent.TimeUnit

/**
 * Governance Mode's read of a single site: same honest indicator/priority
 * data as Field Mode's Site Analysis, plus an officer-only Approve action
 * that moves the recommended intervention straight to the Approved stage.
 */
@Composable
fun GovernancePrioritySiteScreen(
    viewModel: GeoWatershedViewModel,
    captureId: Long,
    onBack: () -> Unit,
    onApproved: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var capture by remember(captureId) { mutableStateOf<CaptureEntity?>(null) }
    LaunchedEffect(captureId) {
        capture = viewModel.getCapture(captureId)
    }

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        val currentCapture = capture
        BackHeader(
            title = currentCapture?.siteCode ?: "Priority Site",
            subtitle = if (currentCapture != null) {
                formatCoords(currentCapture.latitude, currentCapture.longitude, currentCapture.accuracyMeters)
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
        val level = priorityLevelFor(currentCapture.priorityScore)
        val overdueDays = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - currentCapture.timestamp)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(top = 18.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            if (level == com.geowatershed.app.ui.components.PriorityLevel.High && overdueDays > 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SeverityPill("RED · HIGH", GWColors.SeverityHigh)
                    Text("OVERDUE $overdueDays DAY${if (overdueDays == 1L) "" else "S"}", style = GWType.labelSmall, color = GWColors.SeverityHigh)
                }
            }
            PriorityScoreCard(score = currentCapture.priorityScore)
            WhyFlaggedCard(explanation = explanation)
            DataCompletenessCard(available = available, total = indicators.size)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionLabel("SITE INDICATORS")
                SiteIndicatorsList(indicators = indicators)
            }
            val isSeedDemo = currentCapture.siteCode == SiteAnalysisCalculator.SEED_SITE_CODE
            val recommendedType = recommendedInterventionFor(currentCapture)
            RecommendedInterventionCard(
                type = recommendedType,
                reasoning = explanation.insight,
                costLabel = if (isSeedDemo) "EST. ₹48,000" else "COST · PENDING SURVEY",
                eligibilityLabel = if (isSeedDemo) "MGNREGA ELIGIBLE" else "ELIGIBILITY · TBD",
                ctaLabel = "Approve",
                onCreate = {
                    viewModel.approveIntervention(recommendedType, currentCapture.id) {
                        onApproved()
                    }
                },
            )
            Text(
                "This recommendation is rule-based, not AI. The officer may override it before approving; " +
                    "the decision is logged against ${viewModel.governanceOfficerName}.",
                style = GWType.meta,
                color = GWColors.Ink500,
            )
        }
    }
}
