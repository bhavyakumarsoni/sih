package com.geowatershed.app.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.preference.PreferenceManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.geowatershed.app.data.PriorityZoneClassifier
import com.geowatershed.app.data.db.CaptureEntity
import com.geowatershed.app.data.db.InterventionEntity
import com.geowatershed.app.data.model.PriorityZone
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

/** A single pin to plot on the priority map, already classified into a zone. */
data class MapPin(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val title: String,
    val score: Int,
    val zone: PriorityZone,
)

/**
 * Builds a pin for this capture, or null when it has no GPS fix. A capture
 * with no coordinates is simply not plotted — it is never placed at 0,0 or
 * at the watershed centroid, which would invent a location the app does not
 * have.
 */
fun CaptureEntity.toMapPin(interventions: List<InterventionEntity>): MapPin? {
    val lat = latitude ?: return null
    val lon = longitude ?: return null
    return MapPin(
        id = id,
        latitude = lat,
        longitude = lon,
        title = siteCode,
        score = priorityScore,
        zone = PriorityZoneClassifier.zoneFor(this, interventions),
    )
}

/** A flat circular pin in the zone's colour with a white ring, drawn at runtime. */
private fun zonePinDrawable(context: Context, zone: PriorityZone): Drawable {
    val density = context.resources.displayMetrics.density
    val size = (24 * density).toInt().coerceAtLeast(8)
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    val centre = size / 2f
    val radius = centre - (2f * density)

    val fill = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = zone.color.toArgb()
        style = Paint.Style.FILL
    }
    val ring = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = android.graphics.Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 2f * density
    }
    canvas.drawCircle(centre, centre, radius, fill)
    canvas.drawCircle(centre, centre, radius, ring)
    return BitmapDrawable(context.resources, bitmap)
}

/**
 * A real, live OpenStreetMap view (osmdroid — no API key or billing needed)
 * with one colour-coded pin per geo-tagged capture.
 *
 * Set [interactive] to false for an overview map embedded in a scrolling
 * screen: the map then takes no touches at all, so panning it cannot fight
 * the page scroll, and the whole surface becomes a single tap target for
 * [onSurfaceTap].
 */
@Composable
fun PriorityMap(
    pins: List<MapPin>,
    modifier: Modifier = Modifier,
    height: Dp? = 260.dp,
    interactive: Boolean = true,
    onPinTap: (Long) -> Unit = {},
    onSurfaceTap: (() -> Unit)? = null,
    /**
     * Change this to make the map re-frame itself on the current [pins] —
     * pass the active zone filter, for example. While it stays the same the
     * map is left alone, so a user panning an interactive map is never
     * yanked back to centre.
     */
    recenterKey: Any? = null,
) {
    val context = LocalContext.current
    remember {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        Configuration.getInstance().userAgentValue = context.packageName
        Configuration.getInstance().osmdroidBasePath = context.cacheDir.resolve("osmdroid")
        Configuration.getInstance().osmdroidTileCache = context.cacheDir.resolve("osmdroid/tiles")
        true
    }

    // Centre on the first frame that actually has pins (Room delivers captures
    // asynchronously, so centring only in `factory` would leave the map parked
    // on the fallback coordinate forever), and again whenever [recenterKey]
    // changes — that is what makes selecting a zone point the map at it.
    val notCentredYet = remember { Any() }
    var centredFor by remember { mutableStateOf<Any?>(notCentredYet) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(if (height != null) Modifier.height(height) else Modifier)
            .clip(RoundedCornerShape(14.dp)),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(interactive)
                    setMinZoomLevel(3.0)
                    setMaxZoomLevel(19.0)
                    controller.setZoom(11.0)
                    controller.setCenter(GeoPoint(13.1362, 78.1329)) // Chinnahalli, Kolar Taluk
                }
            },
            update = { mapView ->
                mapView.overlays.clear()
                pins.forEach { pin ->
                    val marker = Marker(mapView)
                    marker.position = GeoPoint(pin.latitude, pin.longitude)
                    marker.title = "${pin.title} · ${pin.zone.label}"
                    marker.snippet = "Priority ${pin.score}/100 · ${pin.zone.sub}"
                    marker.icon = zonePinDrawable(mapView.context, pin.zone)
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                    marker.setOnMarkerClickListener { _, _ ->
                        onPinTap(pin.id)
                        true
                    }
                    mapView.overlays.add(marker)
                }
                if (pins.isNotEmpty() && centredFor != recenterKey) {
                    mapView.controller.setZoom(if (pins.size == 1) 15.0 else 13.0)
                    mapView.controller.setCenter(
                        GeoPoint(pins.map { it.latitude }.average(), pins.map { it.longitude }.average()),
                    )
                    centredFor = recenterKey
                }
                mapView.invalidate()
            },
        )

        // Swallow all touches when embedded in a scrolling page.
        if (!interactive && onSurfaceTap != null) {
            Box(modifier = Modifier.matchParentSize().clickable(onClick = onSurfaceTap))
        }
    }
}

/** The five-zone key. Shown wherever pins are, so a colour is never unexplained. */
@Composable
fun PriorityZoneLegend(
    counts: Map<PriorityZone, Int>,
    modifier: Modifier = Modifier,
    selected: PriorityZone? = null,
    onSelect: ((PriorityZone?) -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        PriorityZone.entries.forEach { zone ->
            val isSelected = selected == zone
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(9.dp))
                    .background(if (isSelected) zone.color.copy(alpha = 0.14f) else GWColors.NeutralSurface)
                    .then(
                        if (onSelect == null) {
                            Modifier
                        } else {
                            Modifier.clickable { onSelect(if (isSelected) null else zone) }
                        },
                    )
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(11.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 19.dp else 15.dp)
                        .background(zone.color, CircleShape),
                )
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(zone.label, style = GWType.bodyBold.copy(fontSize = 15.sp), color = GWColors.Ink900)
                    Text(zone.sub, style = GWType.meta, color = GWColors.Ink400)
                }
                Text(
                    "${counts[zone] ?: 0}",
                    style = GWType.dataMonoMedium,
                    color = GWColors.Ink600,
                )
            }
        }
    }
}
