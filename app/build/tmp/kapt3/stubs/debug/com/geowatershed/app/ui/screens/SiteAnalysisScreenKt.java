package com.geowatershed.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000\u001a\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u00a8\u0006\u0013"}, d2 = {"SiteAnalysisScreen", "", "viewModel", "Lcom/geowatershed/app/data/GeoWatershedViewModel;", "captureId", "", "onBack", "Lkotlin/Function0;", "onInterventionCreated", "modifier", "Landroidx/compose/ui/Modifier;", "recommendationReasoning", "", "capture", "Lcom/geowatershed/app/data/db/CaptureEntity;", "indicators", "", "Lcom/geowatershed/app/data/model/SiteIndicator;", "recommendedInterventionFor", "app_debug"})
public final class SiteAnalysisScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SiteAnalysisScreen(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.GeoWatershedViewModel viewModel, long captureId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onInterventionCreated, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * A simple, honest suggestion until real intervention-design rules are wired up from the backend.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String recommendedInterventionFor(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String recommendationReasoning(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.model.SiteIndicator> indicators) {
        return null;
    }
}