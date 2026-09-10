package com.geowatershed.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/geowatershed/app/ui/screens/InterventionFilter;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "matches", "", "stage", "Lcom/geowatershed/app/data/model/InterventionStage;", "All", "Proposed", "Active", "Done", "app_debug"})
enum InterventionFilter {
    /*public static final*/ All /* = new All(null) */,
    /*public static final*/ Proposed /* = new Proposed(null) */,
    /*public static final*/ Active /* = new Active(null) */,
    /*public static final*/ Done /* = new Done(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    
    InterventionFilter(java.lang.String label) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    public final boolean matches(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.InterventionStage stage) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.geowatershed.app.ui.screens.InterventionFilter> getEntries() {
        return null;
    }
}