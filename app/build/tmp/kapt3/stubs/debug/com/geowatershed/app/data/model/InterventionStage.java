package com.geowatershed.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0019\u0010\u0004\u001a\u00020\u0005\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00008F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006\u0018"}, d2 = {"Lcom/geowatershed/app/data/model/InterventionStage;", "", "label", "", "color", "Landroidx/compose/ui/graphics/Color;", "(Ljava/lang/String;ILjava/lang/String;J)V", "getColor-0d7_KjU", "()J", "J", "isDone", "", "()Z", "getLabel", "()Ljava/lang/String;", "next", "getNext", "()Lcom/geowatershed/app/data/model/InterventionStage;", "Proposed", "Approved", "UnderConstruction", "Completed", "Monitoring", "Companion", "app_debug"})
public enum InterventionStage {
    /*public static final*/ Proposed /* = new Proposed(null, 0L) */,
    /*public static final*/ Approved /* = new Approved(null, 0L) */,
    /*public static final*/ UnderConstruction /* = new UnderConstruction(null, 0L) */,
    /*public static final*/ Completed /* = new Completed(null, 0L) */,
    /*public static final*/ Monitoring /* = new Monitoring(null, 0L) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    private final long color = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.model.InterventionStage.Companion Companion = null;
    
    InterventionStage(java.lang.String label, long color) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.geowatershed.app.data.model.InterventionStage getNext() {
        return null;
    }
    
    public final boolean isDone() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.geowatershed.app.data.model.InterventionStage> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/geowatershed/app/data/model/InterventionStage$Companion;", "", "()V", "parse", "Lcom/geowatershed/app/data/model/InterventionStage;", "raw", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Reads a stage from a stored string without throwing.
         *
         * An unreadable value falls back to [Proposed] — the LEAST advanced
         * stage — deliberately. A corrupt or unknown record must never make
         * work look further along than it is: overstating progress is exactly
         * the failure this project refuses everywhere else, and "Completed"
         * is what triggers verification and payment downstream.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.geowatershed.app.data.model.InterventionStage parse(@org.jetbrains.annotations.Nullable()
        java.lang.String raw) {
            return null;
        }
    }
}