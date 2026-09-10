package com.geowatershed.app.data;

/**
 * Assigns each captured site one of the five [PriorityZone] values.
 *
 * The rule, in order of precedence:
 *
 * - work at this site has reached Monitoring   -> Blue  (due for monitoring)
 * - work at this site is Completed             -> Green (work completed)
 * - work is Approved or Under Construction     -> Yellow (work in progress)
 * - no work yet (or only Proposed), score >= 70 -> Red   (high priority)
 * - no work yet (or only Proposed), score < 70  -> Orange (can be delayed)
 *
 * Sites are matched to interventions by [CaptureEntity.siteCode], and where a
 * site has several interventions the furthest-advanced one decides the zone.
 *
 * Note what this deliberately does NOT do: it never treats an unavailable
 * indicator as a low score, and it never reads "no intervention recorded" as
 * "nothing needed here". Both of those would be the missing-equals-zero
 * mistake wearing a different hat.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/geowatershed/app/data/PriorityZoneClassifier;", "", "()V", "HIGH_PRIORITY_SCORE", "", "zoneFor", "Lcom/geowatershed/app/data/model/PriorityZone;", "capture", "Lcom/geowatershed/app/data/db/CaptureEntity;", "interventions", "", "Lcom/geowatershed/app/data/db/InterventionEntity;", "app_debug"})
public final class PriorityZoneClassifier {
    
    /**
     * Matches the HIGH threshold already used by `priorityLevelFor`.
     */
    public static final int HIGH_PRIORITY_SCORE = 70;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.PriorityZoneClassifier INSTANCE = null;
    
    private PriorityZoneClassifier() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geowatershed.app.data.model.PriorityZone zoneFor(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.db.InterventionEntity> interventions) {
        return null;
    }
}