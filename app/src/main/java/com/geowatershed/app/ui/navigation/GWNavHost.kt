package com.geowatershed.app.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.ui.components.AppMode
import com.geowatershed.app.ui.components.LaunchSequence
import com.geowatershed.app.ui.components.ModeStatusBar
import com.geowatershed.app.ui.screens.AiSettingsScreen
import com.geowatershed.app.ui.screens.CaptureScreen
import com.geowatershed.app.ui.screens.DashboardScreen
import com.geowatershed.app.ui.screens.EntryScreen
import com.geowatershed.app.ui.screens.GovernanceDashboardScreen
import com.geowatershed.app.ui.screens.GovernanceLoginScreen
import com.geowatershed.app.ui.screens.GovernancePipelineScreen
import com.geowatershed.app.ui.screens.GovernancePrioritySiteScreen
import com.geowatershed.app.ui.screens.InterventionsScreen
import com.geowatershed.app.ui.screens.MonitoringScreen
import com.geowatershed.app.ui.screens.PriorityMapScreen
import com.geowatershed.app.ui.screens.SiteAnalysisScreen

@Composable
fun GWNavHost() {
    val navController = rememberNavController()
    val viewModel: GeoWatershedViewModel = viewModel()
    val captures by viewModel.captures.collectAsState()
    var showLaunchSequence by remember { mutableStateOf(true) }

    // The mode indicator is driven by the live route rather than by a flag set
    // at login, so it cannot drift out of step with where the user actually is.
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val mode = when {
        currentRoute == null || currentRoute == Routes.ENTRY -> null
        currentRoute.startsWith("gov_") -> AppMode.Governance
        else -> AppMode.Field
    }

    Box(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.fillMaxSize()) {
    NavHost(
        navController = navController,
        startDestination = Routes.ENTRY,
        modifier = Modifier.weight(1f),
    ) {
        composable(Routes.ENTRY) {
            EntryScreen(
                viewModel = viewModel,
                onChooseField = { navController.navigate(Routes.DASHBOARD) },
                onChooseGovernance = { navController.navigate(Routes.GOV_LOGIN) },
            )
        }

        // ---- Field Mode (no login) ----
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                viewModel = viewModel,
                onCaptureImage = { navController.navigate(Routes.CAPTURE) },
                onViewInterventions = { navController.navigate(Routes.INTERVENTIONS) },
                onOpenMap = { navController.navigate(Routes.PRIORITY_MAP) },
            )
        }
        composable(Routes.CAPTURE) {
            CaptureScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSaveAndAnalyze = { captureId -> navController.navigate(Routes.siteAnalysis(captureId)) },
            )
        }
        composable(
            route = Routes.SITE_ANALYSIS_PATTERN,
            arguments = listOf(navArgument("captureId") { type = NavType.LongType }),
        ) { backStackEntry ->
            SiteAnalysisScreen(
                viewModel = viewModel,
                captureId = backStackEntry.arguments?.getLong("captureId") ?: 0L,
                onBack = { navController.popBackStack() },
                onInterventionCreated = { navController.navigate(Routes.INTERVENTIONS) },
                onOpenAiSettings = { navController.navigate(Routes.AI_SETTINGS) },
            )
        }
        composable(Routes.AI_SETTINGS) {
            AiSettingsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }
        composable(Routes.PRIORITY_MAP) {
            PriorityMapScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOpenSite = { captureId -> navController.navigate(Routes.siteAnalysis(captureId)) },
            )
        }
        composable(Routes.INTERVENTIONS) {
            InterventionsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOpenMonitoring = { iv -> navController.navigate(Routes.monitoring(iv.id)) },
            )
        }
        composable(
            route = Routes.MONITORING_PATTERN,
            arguments = listOf(navArgument("interventionId") { type = NavType.LongType }),
        ) { backStackEntry ->
            MonitoringScreen(
                viewModel = viewModel,
                interventionId = backStackEntry.arguments?.getLong("interventionId") ?: 0L,
                onBack = { navController.popBackStack() },
            )
        }

        // ---- Governance Mode (officer login required, no self-service signup) ----
        composable(Routes.GOV_LOGIN) {
            GovernanceLoginScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onLoggedIn = {
                    navController.navigate(Routes.GOV_DASHBOARD) {
                        popUpTo(Routes.GOV_LOGIN) { inclusive = true }
                    }
                },
            )
        }
        composable(Routes.GOV_DASHBOARD) {
            GovernanceDashboardScreen(
                viewModel = viewModel,
                onOpenSite = { captureId -> navController.navigate(Routes.govPrioritySite(captureId)) },
                onOpenPipeline = { navController.navigate(Routes.GOV_PIPELINE) },
                onOpenMap = { navController.navigate(Routes.GOV_PRIORITY_MAP) },
                onOpenAiSettings = { navController.navigate(Routes.AI_SETTINGS) },
                onLogout = {
                    viewModel.governanceLogout()
                    navController.navigate(Routes.ENTRY) {
                        popUpTo(Routes.ENTRY) { inclusive = true }
                    }
                },
            )
        }
        composable(
            route = Routes.GOV_PRIORITY_SITE_PATTERN,
            arguments = listOf(navArgument("captureId") { type = NavType.LongType }),
        ) { backStackEntry ->
            GovernancePrioritySiteScreen(
                viewModel = viewModel,
                captureId = backStackEntry.arguments?.getLong("captureId") ?: 0L,
                onBack = { navController.popBackStack() },
                onApproved = { navController.navigate(Routes.GOV_PIPELINE) },
            )
        }
        composable(Routes.GOV_PRIORITY_MAP) {
            PriorityMapScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOpenSite = { captureId -> navController.navigate(Routes.govPrioritySite(captureId)) },
            )
        }
        composable(Routes.GOV_PIPELINE) {
            GovernancePipelineScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOpenMonitoring = { iv -> navController.navigate(Routes.govMonitoring(iv.id)) },
            )
        }
        composable(
            route = Routes.GOV_MONITORING_PATTERN,
            arguments = listOf(navArgument("interventionId") { type = NavType.LongType }),
        ) { backStackEntry ->
            MonitoringScreen(
                viewModel = viewModel,
                interventionId = backStackEntry.arguments?.getLong("interventionId") ?: 0L,
                onBack = { navController.popBackStack() },
            )
        }
        }

        if (mode != null) {
            ModeStatusBar(mode = mode, recordsOnDevice = captures.size)
        }
    }

    if (showLaunchSequence) {
        LaunchSequence(onFinished = { showLaunchSequence = false })
    }
    }
}
