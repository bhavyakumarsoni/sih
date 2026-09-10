package com.geowatershed.app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/** A single field capture: geo-tagged photo + observation, created from the Capture screen. */
@Entity(tableName = "captures")
data class CaptureEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val siteCode: String,
    val timestamp: Long,
    val observationType: String,
    val description: String,
    val latitude: Double?,
    val longitude: Double?,
    val accuracyMeters: Float?,
    val photoPath: String?,
    val priorityScore: Int,

    // --- Experimental AI assist. Kept beside the capture, never folded into
    // it: none of these fields feed priorityScore, and aiStatus stays
    // Suggested until a human confirms or rejects. ---
    val aiStatus: String = "NotRequested",
    val aiSuggestedType: String? = null,
    val aiCertainty: String? = null,
    val aiRationale: String? = null,
    val aiDecidedAt: Long? = null,
)
