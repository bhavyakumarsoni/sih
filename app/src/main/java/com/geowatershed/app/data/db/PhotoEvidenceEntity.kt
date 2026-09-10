package com.geowatershed.app.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photo_evidence",
    foreignKeys = [
        ForeignKey(
            entity = InterventionEntity::class,
            parentColumns = ["id"],
            childColumns = ["interventionId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("interventionId")],
)
data class PhotoEvidenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val interventionId: Long,
    val photoSet: String,
    val photoPath: String?,
    val date: String,
    val meta: String,
    val createdAt: Long,
)
