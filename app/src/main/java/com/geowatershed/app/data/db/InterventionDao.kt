package com.geowatershed.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InterventionDao {
    @Insert
    suspend fun insert(intervention: InterventionEntity): Long

    @Update
    suspend fun update(intervention: InterventionEntity)

    @Query("SELECT * FROM interventions ORDER BY id DESC")
    fun observeAll(): Flow<List<InterventionEntity>>

    @Query("SELECT * FROM interventions WHERE id = :id")
    fun observeById(id: Long): Flow<InterventionEntity?>

    @Query("SELECT COUNT(*) FROM interventions")
    suspend fun count(): Int
}
