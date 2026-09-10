package com.geowatershed.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u0003H\'\u00a8\u0006\u000b"}, d2 = {"Lcom/geowatershed/app/data/db/PhotoEvidenceDao;", "", "insert", "", "photo", "Lcom/geowatershed/app/data/db/PhotoEvidenceEntity;", "(Lcom/geowatershed/app/data/db/PhotoEvidenceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeForIntervention", "Lkotlinx/coroutines/flow/Flow;", "", "interventionId", "app_debug"})
@androidx.room.Dao()
public abstract interface PhotoEvidenceDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.PhotoEvidenceEntity photo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM photo_evidence WHERE interventionId = :interventionId ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.geowatershed.app.data.db.PhotoEvidenceEntity>> observeForIntervention(long interventionId);
}