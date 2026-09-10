package com.geowatershed.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CaptureDao {
    @Insert
    suspend fun insert(capture: CaptureEntity): Long

    @Query("SELECT * FROM captures ORDER BY id DESC")
    fun observeAll(): Flow<List<CaptureEntity>>

    @Query("SELECT * FROM captures WHERE id = :id")
    suspend fun getById(id: Long): CaptureEntity?

    @Query("SELECT * FROM captures WHERE id = :id")
    fun observeById(id: Long): Flow<CaptureEntity?>

    @Query("SELECT COUNT(*) FROM captures")
    suspend fun count(): Int

    @Update
    suspend fun update(capture: CaptureEntity)

    @Query("SELECT * FROM captures WHERE aiStatus = :status ORDER BY id ASC")
    suspend fun byAiStatus(status: String): List<CaptureEntity>

    @Query("SELECT COUNT(*) FROM captures WHERE aiStatus = 'Queued'")
    fun observeQueuedForAi(): Flow<Int>
}
