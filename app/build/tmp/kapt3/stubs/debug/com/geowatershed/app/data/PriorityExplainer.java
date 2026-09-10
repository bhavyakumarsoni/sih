package com.geowatershed.app.data;

/**
 * Rule-based explainer: turns a capture and its indicators into the reasons
 * behind its priority classification.
 *
 * This is deliberately rule-based and deliberately on-device. It runs with no
 * network, no backend and no model, so it cannot fail during a demo, and
 * every sentence it produces can be traced to a threshold in this file.
 *
 * Thresholds below are conventional watershed-planning rules of thumb, not
 * values calibrated against a validated dataset — treat them as a defensible
 * starting point that a domain expert should review, not as settled science.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u0014H\u0002\u00a2\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/geowatershed/app/data/PriorityExplainer;", "", "()V", "FAR_STREAM_METRES", "", "HEALTHY_NDVI", "MODERATE_SLOPE_PERCENT", "NEAR_STREAM_METRES", "SPARSE_NDVI", "STEEP_SLOPE_PERCENT", "explain", "Lcom/geowatershed/app/data/PriorityExplanation;", "capture", "Lcom/geowatershed/app/data/db/CaptureEntity;", "indicators", "", "Lcom/geowatershed/app/data/model/SiteIndicator;", "factorFor", "Lcom/geowatershed/app/data/PriorityFactor;", "name", "", "value", "firstNumber", "text", "(Ljava/lang/String;)Ljava/lang/Double;", "insightFor", "raising", "", "missing", "total", "app_debug"})
public final class PriorityExplainer {
    private static final double STEEP_SLOPE_PERCENT = 15.0;
    private static final double MODERATE_SLOPE_PERCENT = 8.0;
    private static final double NEAR_STREAM_METRES = 150.0;
    private static final double FAR_STREAM_METRES = 500.0;
    private static final double SPARSE_NDVI = 0.3;
    private static final double HEALTHY_NDVI = 0.5;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.PriorityExplainer INSTANCE = null;
    
    private PriorityExplainer() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geowatershed.app.data.PriorityExplanation explain(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.model.SiteIndicator> indicators) {
        return null;
    }
    
    private final com.geowatershed.app.data.PriorityFactor factorFor(java.lang.String name, java.lang.String value) {
        return null;
    }
    
    private final java.lang.String insightFor(int raising, int missing, int total) {
        return null;
    }
    
    /**
     * Pulls the first number out of a display string like "14.2%", "82 m", "0.21 sparse".
     */
    private final java.lang.Double firstNumber(java.lang.String text) {
        return null;
    }
}