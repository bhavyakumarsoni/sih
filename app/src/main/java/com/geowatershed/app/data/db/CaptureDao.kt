package com.geowatershed.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CaptureDao {
    @Insert
    suspend fun insert(capture: CaptureEntity): Long

    @Query("SELECT * FROM captures ORDER BY id DESC")
    fun observeAll(): Flow<List<CaptureEntity>>

    @Query("SELECT * FROM captures WHERE id = :id")
    suspend fun getById(id: Long): CaptureEntity?

    @Query("SELECT COUNT(*) FROM captures")
    suspend fun count(): Int
}
