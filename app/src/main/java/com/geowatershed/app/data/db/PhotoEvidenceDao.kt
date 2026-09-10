package com.geowatershed.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoEvidenceDao {
    @Insert
    suspend fun insert(photo: PhotoEvidenceEntity): Long

    @Query("SELECT * FROM photo_evidence WHERE interventionId = :interventionId ORDER BY id ASC")
    fun observeForIntervention(interventionId: Long): Flow<List<PhotoEvidenceEntity>>
}
