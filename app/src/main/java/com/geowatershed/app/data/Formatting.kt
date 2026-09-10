package com.geowatershed.app.data

import java.util.Locale

/** Shared display formatting for a real (or missing) GPS fix. */
fun formatCoords(latitude: Double?, longitude: Double?, accuracyMeters: Float?): String {
    if (latitude == null || longitude == null) return "GPS unavailable"
    val accuracy = accuracyMeters?.let { " · ±${it.toInt()} m" }.orEmpty()
    return String.format(Locale.US, "%.5f N, %.5f E", latitude, longitude) + accuracy
}
