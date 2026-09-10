package com.geowatershed.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010!J\t\u0010)\u001a\u00020\u0011H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010.\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010/\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u0084\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001\u00a2\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u00112\b\u00105\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00106\u001a\u000207H\u00d6\u0001J\t\u00108\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u00a8\u00069"}, d2 = {"Lcom/geowatershed/app/data/db/InterventionEntity;", "", "id", "", "type", "", "siteCode", "siteDetail", "latitude", "", "longitude", "accuracyMeters", "", "stage", "createdAt", "sourceCaptureId", "verificationSubmitted", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/String;JLjava/lang/Long;Z)V", "getAccuracyMeters", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getCreatedAt", "()J", "getId", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude", "getSiteCode", "()Ljava/lang/String;", "getSiteDetail", "getSourceCaptureId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStage", "getType", "getVerificationSubmitted", "()Z", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/String;JLjava/lang/Long;Z)Lcom/geowatershed/app/data/db/InterventionEntity;", "equals", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "interventions")
public final class InterventionEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String siteCode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String siteDetail = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double latitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double longitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float accuracyMeters = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String stage = null;
    private final long createdAt = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long sourceCaptureId = null;
    private final boolean verificationSubmitted = false;
    
    public InterventionEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String siteCode, @org.jetbrains.annotations.NotNull()
    java.lang.String siteDetail, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float accuracyMeters, @org.jetbrains.annotations.NotNull()
    java.lang.String stage, long createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long sourceCaptureId, boolean verificationSubmitted) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSiteCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSiteDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getAccuracyMeters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStage() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSourceCaptureId() {
        return null;
    }
    
    public final boolean getVerificationSubmitted() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geowatershed.app.data.db.InterventionEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String siteCode, @org.jetbrains.annotations.NotNull()
    java.lang.String siteDetail, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float accuracyMeters, @org.jetbrains.annotations.NotNull()
    java.lang.String stage, long createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long sourceCaptureId, boolean verificationSubmitted) {
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