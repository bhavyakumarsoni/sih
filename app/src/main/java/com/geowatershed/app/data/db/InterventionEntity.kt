package com.geowatershed.app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interventions")
data class InterventionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val siteCode: String,
    val siteDetail: String,
    val latitude: Double?,
    val longitude: Double?,
    val accuracyMeters: Float?,
    val stage: String,
    val createdAt: Long,
    val sourceCaptureId: Long?,
    val verificationSubmitted: Boolean = false,
)
