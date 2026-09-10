package com.geowatershed.app.data;

/**
 * A full account of why a site scored what it scored.
 *
 * [unavailable] is a first-class part of the answer, not an afterthought:
 * a site is never explained as if the data it lacks were evidence of safety.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JI\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/geowatershed/app/data/PriorityExplanation;", "", "headline", "", "contributing", "", "Lcom/geowatershed/app/data/PriorityFactor;", "unavailable", "insight", "caveat", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getCaveat", "()Ljava/lang/String;", "getContributing", "()Ljava/util/List;", "getHeadline", "getInsight", "getUnavailable", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class PriorityExplanation {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String headline = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.geowatershed.app.data.PriorityFactor> contributing = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> unavailable = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String insight = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String caveat = null;
    
    public PriorityExplanation(@org.jetbrains.annotations.NotNull()
    java.lang.String headline, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.PriorityFactor> contributing, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> unavailable, @org.jetbrains.annotations.NotNull()
    java.lang.String insight, @org.jetbrains.annotations.Nullable()
    java.lang.String caveat) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHeadline() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.geowatershed.app.data.PriorityFactor> getContributing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getUnavailable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInsight() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCaveat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.geowatershed.app.data.PriorityFactor> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geowatershed.app.data.PriorityExplanation copy(@org.jetbrains.annotations.NotNull()
    java.lang.String headline, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.PriorityFactor> contributing, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> unavailable, @org.jetbrains.annotations.NotNull()
    java.lang.String insight, @org.jetbrains.annotations.Nullable()
    java.lang.String caveat) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}