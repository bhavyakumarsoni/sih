package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"ModeStatusBar", "", "mode", "Lcom/geowatershed/app/ui/components/AppMode;", "recordsOnDevice", "", "modifier", "Landroidx/compose/ui/Modifier;", "app_debug"})
public final class ModeStatusBarKt {
    
    /**
     * A persistent strip pinned below every screen, carrying the two facts a
     * field user must never have to guess: which mode they are in, and whether
     * their work is actually saved.
     *
     * The storage wording is deliberately literal. There is no server to sync
     * to, so this does not promise a sync that cannot happen — it states what is
     * true, which is that the records are on this device.
     */
    @androidx.compose.runtime.Composable()
    public static final void ModeStatusBar(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.ui.components.AppMode mode, int recordsOnDevice, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}