package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"gpsCoordStyle", "Landroidx/compose/ui/text/TextStyle;", "gpsTitleStyle", "GpsCard", "", "fix", "Lcom/geowatershed/app/data/GpsFix;", "searching", "", "permissionGranted", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "app_debug"})
public final class GpsCardKt {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.compose.ui.text.TextStyle gpsTitleStyle = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.compose.ui.text.TextStyle gpsCoordStyle = null;
    
    /**
     * The GPS status card on the Capture screen, backed by a real one-shot
     * device location fix. Tapping it retries the fix.
     */
    @androidx.compose.runtime.Composable()
    public static final void GpsCard(@org.jetbrains.annotations.Nullable()
    com.geowatershed.app.data.GpsFix fix, boolean searching, boolean permissionGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}