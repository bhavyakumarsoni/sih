package com.geowatershed.app.data;

/**
 * Turns a [CaptureEntity] into the six site indicators shown on the Site
 * Analysis screen, and scores it. There is no GIS/remote-sensing backend
 * wired up yet, so Slope, Distance to Stream, Vegetation, Existing
 * Structure, and Agricultural Land are honest gaps (UNAVAILABLE) for any
 * capture taken in the field today — only Soil Erosion can be filled in,
 * and only when that's what the field worker actually reported. The demo
 * seed record (SITE-0142) is the one exception, pre-populated with the
 * full backend-style dataset from the original design mock.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/geowatershed/app/data/SiteAnalysisCalculator;", "", "()V", "SEED_INDICATORS", "", "Lcom/geowatershed/app/data/model/SiteIndicator;", "SEED_SITE_CODE", "", "indicatorsFor", "capture", "Lcom/geowatershed/app/data/db/CaptureEntity;", "scoreFor", "", "observationType", "Lcom/geowatershed/app/data/model/ObservationType;", "app_debug"})
public final class SiteAnalysisCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEED_SITE_CODE = "SITE-0142";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.geowatershed.app.data.model.SiteIndicator> SEED_INDICATORS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.SiteAnalysisCalculator INSTANCE = null;
    
    private SiteAnalysisCalculator() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.geowatershed.app.data.model.SiteIndicator> indicatorsFor(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture) {
        return null;
    }
    
    /**
     * A provisional score from only the indicators available today — never a fake precise GIS score.
     */
    public final int scoreFor(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.ObservationType observationType) {
        return 0;
    }
}