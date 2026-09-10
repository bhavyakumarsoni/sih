package com.geowatershed.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0003\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\nH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"STALLED_MS", "", "GovernancePipelineScreen", "", "viewModel", "Lcom/geowatershed/app/data/GeoWatershedViewModel;", "onBack", "Lkotlin/Function0;", "onOpenMonitoring", "Lkotlin/Function1;", "Lcom/geowatershed/app/data/db/InterventionEntity;", "modifier", "Landroidx/compose/ui/Modifier;", "rememberSortedInterventions", "", "interventions", "isStalled", "", "app_debug"})
public final class GovernancePipelineScreenKt {
    private static final long STALLED_MS = 2592000000L;
    
    private static final boolean isStalled(com.geowatershed.app.data.db.InterventionEntity $this$isStalled) {
        return false;
    }
    
    /**
     * Governance Mode's pipeline view: same interventions as Field Mode's list,
     * reordered with stalled work (no stage change in 30+ days) surfaced first
     * and flagged red, plus a completion-rate summary.
     */
    @androidx.compose.runtime.Composable()
    public static final void GovernancePipelineScreen(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.GeoWatershedViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.geowatershed.app.data.db.InterventionEntity, kotlin.Unit> onOpenMonitoring, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final java.util.List<com.geowatershed.app.data.db.InterventionEntity> rememberSortedInterventions(java.util.List<com.geowatershed.app.data.db.InterventionEntity> interventions) {
        return null;
    }
}