package com.geowatershed.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\'J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\'J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/geowatershed/app/data/db/InterventionDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "intervention", "Lcom/geowatershed/app/data/db/InterventionEntity;", "(Lcom/geowatershed/app/data/db/InterventionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "observeById", "id", "update", "", "app_debug"})
@androidx.room.Dao()
public abstract interface InterventionDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.InterventionEntity intervention, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.InterventionEntity intervention, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM interventions ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.geowatershed.app.data.db.InterventionEntity>> observeAll();
    
    @androidx.room.Query(value = "SELECT * FROM interventions WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.geowatershed.app.data.db.InterventionEntity> observeById(long id);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM interventions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}