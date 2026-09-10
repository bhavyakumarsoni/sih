package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0003\u001a\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001aH\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a \u0010\u0015\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u001a\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\u0003\u00a8\u0006\u001e"}, d2 = {"DataCompletenessCard", "", "available", "", "total", "modifier", "Landroidx/compose/ui/Modifier;", "FactorRow", "factor", "Lcom/geowatershed/app/data/PriorityFactor;", "PriorityScoreCard", "score", "RecommendedInterventionCard", "type", "", "reasoning", "costLabel", "eligibilityLabel", "ctaLabel", "onCreate", "Lkotlin/Function0;", "SiteIndicatorsList", "indicators", "", "Lcom/geowatershed/app/data/model/SiteIndicator;", "WhyFlaggedCard", "explanation", "Lcom/geowatershed/app/data/PriorityExplanation;", "priorityLevelFor", "Lcom/geowatershed/app/ui/components/PriorityLevel;", "app_debug"})
public final class SiteAnalysisComponentsKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.ui.components.PriorityLevel priorityLevelFor(int score) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PriorityScoreCard(int score, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DataCompletenessCard(int available, int total, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SiteIndicatorsList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.model.SiteIndicator> indicators, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RecommendedInterventionCard(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String reasoning, @org.jetbrains.annotations.NotNull()
    java.lang.String costLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String eligibilityLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String ctaLabel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCreate, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * "Why this is flagged" — the reasoning behind a site's classification, so a
     * priority score is never a bare number an officer has to take on trust.
     *
     * Unavailable indicators are shown here too, in the same dashed treatment
     * used everywhere else, because what the app does not know is part of the
     * answer rather than a gap to be quietly skipped.
     */
    @androidx.compose.runtime.Composable()
    public static final void WhyFlaggedCard(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.PriorityExplanation explanation, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FactorRow(com.geowatershed.app.data.PriorityFactor factor) {
    }
}