package com.geowatershed.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CaptureEntity::class, InterventionEntity::class, PhotoEvidenceEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class GeoWatershedDatabase : RoomDatabase() {
    abstract fun captureDao(): CaptureDao
    abstract fun interventionDao(): InterventionDao
    abstract fun photoEvidenceDao(): PhotoEvidenceDao

    companion object {
        @Volatile
        private var instance: GeoWatershedDatabase? = null

        fun getInstance(context: Context): GeoWatershedDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GeoWatershedDatabase::class.java,
                    "geowatershed.db",
                ).fallbackToDestructiveMigration().build().also { instance = it }
            }
    }
}
