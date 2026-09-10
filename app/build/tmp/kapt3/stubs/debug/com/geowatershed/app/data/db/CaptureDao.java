package com.geowatershed.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000f0\u000eH\'\u00a8\u0006\u0010"}, d2 = {"Lcom/geowatershed/app/data/db/CaptureDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "Lcom/geowatershed/app/data/db/CaptureEntity;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "capture", "(Lcom/geowatershed/app/data/db/CaptureEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "app_debug"})
@androidx.room.Dao()
public abstract interface CaptureDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM captures ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.geowatershed.app.data.db.CaptureEntity>> observeAll();
    
    @androidx.room.Query(value = "SELECT * FROM captures WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geowatershed.app.data.db.CaptureEntity> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM captures")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}