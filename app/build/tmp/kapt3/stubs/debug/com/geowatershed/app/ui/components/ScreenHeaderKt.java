package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a2\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\u000f"}, d2 = {"BackHeader", "", "title", "", "subtitle", "onBack", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "DashboardHeader", "watershedName", "watershedSub", "gpsStatus", "gpsActive", "", "app_debug"})
public final class ScreenHeaderKt {
    
    /**
     * Dark app-bar header with a back chevron, used by every screen except the dashboard.
     */
    @androidx.compose.runtime.Composable()
    public static final void BackHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Dashboard's own header: wordmark + GPS indicator, watershed name and location line.
     */
    @androidx.compose.runtime.Composable()
    public static final void DashboardHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String watershedName, @org.jetbrains.annotations.NotNull()
    java.lang.String watershedSub, @org.jetbrains.annotations.NotNull()
    java.lang.String gpsStatus, boolean gpsActive, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}