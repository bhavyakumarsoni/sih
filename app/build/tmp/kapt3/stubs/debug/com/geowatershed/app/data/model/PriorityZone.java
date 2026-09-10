package com.geowatershed.app.data.model;

/**
 * The five map zones from the design mock's ZONES table, with its exact
 * colour values. These are the only five states a site pin can be in.
 *
 * A zone is a function of two things that are actually known: the site's
 * priority score, and how far the work at that site has genuinely
 * progressed. A zone is never derived from missing data — a site with no
 * GIS coverage is not thereby "low priority", and absence of an
 * intervention is not evidence that none is needed.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B7\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nR\u0019\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\t\u001a\u00020\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006\u0019"}, d2 = {"Lcom/geowatershed/app/data/model/PriorityZone;", "", "key", "", "label", "sub", "short", "color", "Landroidx/compose/ui/graphics/Color;", "ink", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getColor-0d7_KjU", "()J", "J", "getInk-0d7_KjU", "getKey", "()Ljava/lang/String;", "getLabel", "getShort", "getSub", "Red", "Orange", "Yellow", "Green", "Blue", "app_debug"})
public enum PriorityZone {
    /*public static final*/ Red /* = new Red(null, null, null, null, 0L, 0L) */,
    /*public static final*/ Orange /* = new Orange(null, null, null, null, 0L, 0L) */,
    /*public static final*/ Yellow /* = new Yellow(null, null, null, null, 0L, 0L) */,
    /*public static final*/ Green /* = new Green(null, null, null, null, 0L, 0L) */,
    /*public static final*/ Blue /* = new Blue(null, null, null, null, 0L, 0L) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String key = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sub = null;
    private final long color = 0L;
    private final long ink = 0L;
    
    PriorityZone(java.lang.String key, java.lang.String label, java.lang.String sub, java.lang.String p3_54706750, long color, long ink) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSub() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getShort() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.geowatershed.app.data.model.PriorityZone> getEntries() {
        return null;
    }
}