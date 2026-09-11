package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ar\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\b\u0012\u001aL\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00152\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00162\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\fH\u0007\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0002\u001a\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u0004*\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006#"}, d2 = {"PriorityMap", "", "pins", "", "Lcom/geowatershed/app/ui/components/MapPin;", "modifier", "Landroidx/compose/ui/Modifier;", "height", "Landroidx/compose/ui/unit/Dp;", "interactive", "", "onPinTap", "Lkotlin/Function1;", "", "onSurfaceTap", "Lkotlin/Function0;", "recenterKey", "", "PriorityMap-u8CUcSQ", "PriorityZoneLegend", "counts", "", "Lcom/geowatershed/app/data/model/PriorityZone;", "", "selected", "onSelect", "zonePinDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "zone", "toMapPin", "Lcom/geowatershed/app/data/db/CaptureEntity;", "interventions", "Lcom/geowatershed/app/data/db/InterventionEntity;", "app_debug"})
public final class PriorityMapKt {
    
    /**
     * Builds a pin for this capture, or null when it has no GPS fix. A capture
     * with no coordinates is simply not plotted — it is never placed at 0,0 or
     * at the watershed centroid, which would invent a location the app does not
     * have.
     */
    @org.jetbrains.annotations.Nullable()
    public static final com.geowatershed.app.ui.components.MapPin toMapPin(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity $this$toMapPin, @org.jetbrains.annotations.NotNull()
    java.util.List<com.geowatershed.app.data.db.InterventionEntity> interventions) {
        return null;
    }
    
    /**
     * A flat circular pin in the zone's colour with a white ring, drawn at runtime.
     */
    private static final android.graphics.drawable.Drawable zonePinDrawable(android.content.Context context, com.geowatershed.app.data.model.PriorityZone zone) {
        return null;
    }
    
    /**
     * The five-zone key. Shown wherever pins are, so a colour is never unexplained.
     */
    @androidx.compose.runtime.Composable()
    public static final void PriorityZoneLegend(@org.jetbrains.annotations.NotNull()
    java.util.Map<com.geowatershed.app.data.model.PriorityZone, java.lang.Integer> counts, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.Nullable()
    com.geowatershed.app.data.model.PriorityZone selected, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.geowatershed.app.data.model.PriorityZone, kotlin.Unit> onSelect) {
    }
}