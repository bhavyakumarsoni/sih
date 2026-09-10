package com.geowatershed.app.ui.navigation

object Routes {
    const val ENTRY = "entry"
    const val DASHBOARD = "dashboard"
    const val CAPTURE = "capture"
    const val SITE_ANALYSIS_PATTERN = "site_analysis/{captureId}"
    const val INTERVENTIONS = "interventions"
    const val MONITORING_PATTERN = "monitoring/{interventionId}"

    const val GOV_LOGIN = "gov_login"
    const val GOV_DASHBOARD = "gov_dashboard"
    const val GOV_PRIORITY_SITE_PATTERN = "gov_priority_site/{captureId}"
    const val GOV_PIPELINE = "gov_pipeline"
    const val GOV_MONITORING_PATTERN = "gov_monitoring/{interventionId}"

    fun siteAnalysis(captureId: Long): String = "site_analysis/$captureId"
    fun monitoring(interventionId: Long): String = "monitoring/$interventionId"
    fun govPrioritySite(captureId: Long): String = "gov_priority_site/$captureId"
    fun govMonitoring(interventionId: Long): String = "gov_monitoring/$interventionId"
}
