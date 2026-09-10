package com.geowatershed.app.ui.components

import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.geowatershed.app.data.db.CaptureEntity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

/** A single pin to plot on the priority map. */
data class MapPin(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val title: String,
    val isHighPriority: Boolean,
)

fun CaptureEntity.toMapPin(): MapPin? {
    val lat = latitude ?: return null
    val lon = longitude ?: return null
    return MapPin(id = id, latitude = lat, longitude = lon, title = siteCode, isHighPriority = priorityScore >= 70)
}

/**
 * A real, live OpenStreetMap view (osmdroid — no API key or billing needed)
 * centered on the watershed, with a pin per geo-tagged capture. Tapping a
 * pin reports its capture id.
 */
@Composable
fun PriorityMap(
    pins: List<MapPin>,
    modifier: Modifier = Modifier,
    height: Dp = 260.dp,
    onPinTap: (Long) -> Unit = {},
) {
    val context = LocalContext.current
    remember {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        Configuration.getInstance().userAgentValue = context.packageName
        Configuration.getInstance().osmdroidTileCache = context.cacheDir.resolve("osmdroid")
        true
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(14.dp)),
        factory = { ctx ->
            MapView(ctx).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                minZoomLevel = 3.0
                maxZoomLevel = 19.0
                val center = if (pins.isNotEmpty()) {
                    GeoPoint(pins.map { it.latitude }.average(), pins.map { it.longitude }.average())
                } else {
                    GeoPoint(13.1362, 78.1329) // Chinnahalli watershed, Kolar Taluk
                }
                controller.setZoom(if (pins.isEmpty()) 11.0 else 13.0)
                controller.setCenter(center)
            }
        },
        update = { mapView ->
            mapView.overlays.clear()
            pins.forEach { pin ->
                val marker = Marker(mapView)
                marker.position = GeoPoint(pin.latitude, pin.longitude)
                marker.title = pin.title
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.setOnMarkerClickListener { _, _ ->
                    onPinTap(pin.id)
                    true
                }
                mapView.overlays.add(marker)
            }
            mapView.invalidate()
        },
    )
}
