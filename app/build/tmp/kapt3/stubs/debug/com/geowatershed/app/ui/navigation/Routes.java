package com.geowatershed.app.ui.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/geowatershed/app/ui/navigation/Routes;", "", "()V", "AI_SETTINGS", "", "CAPTURE", "DASHBOARD", "ENTRY", "GOV_DASHBOARD", "GOV_LOGIN", "GOV_MONITORING_PATTERN", "GOV_PIPELINE", "GOV_PRIORITY_MAP", "GOV_PRIORITY_SITE_PATTERN", "INTERVENTIONS", "MONITORING_PATTERN", "PRIORITY_MAP", "SITE_ANALYSIS_PATTERN", "govMonitoring", "interventionId", "", "govPrioritySite", "captureId", "monitoring", "siteAnalysis", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ENTRY = "entry";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DASHBOARD = "dashboard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CAPTURE = "capture";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SITE_ANALYSIS_PATTERN = "site_analysis/{captureId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTERVENTIONS = "interventions";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRIORITY_MAP = "priority_map";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AI_SETTINGS = "ai_settings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MONITORING_PATTERN = "monitoring/{interventionId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_LOGIN = "gov_login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_DASHBOARD = "gov_dashboard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_PRIORITY_SITE_PATTERN = "gov_priority_site/{captureId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_PIPELINE = "gov_pipeline";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_PRIORITY_MAP = "gov_priority_map";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOV_MONITORING_PATTERN = "gov_monitoring/{interventionId}";
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.ui.navigation.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String siteAnalysis(long captureId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String monitoring(long interventionId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String govPrioritySite(long captureId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String govMonitoring(long interventionId) {
        return null;
    }
}