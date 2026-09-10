package com.geowatershed.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.ui.screens.CaptureScreen
import com.geowatershed.app.ui.screens.DashboardScreen
import com.geowatershed.app.ui.screens.EntryScreen
import com.geowatershed.app.ui.screens.GovernanceDashboardScreen
import com.geowatershed.app.ui.screens.GovernanceLoginScreen
import com.geowatershed.app.ui.screens.GovernancePipelineScreen
import com.geowatershed.app.ui.screens.GovernancePrioritySiteScreen
import com.geowatershed.app.ui.screens.InterventionsScreen
import com.geowatershed.app.ui.screens.MonitoringScreen
import com.geowatershed.app.ui.screens.SiteAnalysisScreen

@Composable
fun GWNavHost() {
    val navController = rememberNavController()
    val viewModel: GeoWatershedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.ENTRY) {
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
}
