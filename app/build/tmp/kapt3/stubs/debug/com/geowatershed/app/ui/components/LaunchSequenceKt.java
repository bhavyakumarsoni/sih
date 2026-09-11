package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"TOTAL_MS", "", "LaunchSequence", "", "onFinished", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "teardrop", "Landroidx/compose/ui/graphics/Path;", "cx", "", "cy", "radius", "tip", "app_debug"})
public final class LaunchSequenceKt {
    private static final int TOTAL_MS = 1200;
    
    /**
     * The launch sequence: a water drop settles, then turns over and becomes a
     * location pin, establishing the app's subject — water, and where it is.
     *
     * The morph is the whole trick and it is nearly free: a water drop and a map
     * pin are the same teardrop, pointing opposite ways. So this rotates one
     * shape 180 degrees and opens a hole in it rather than interpolating between
     * two different paths.
     *
     * This draws OVER the app rather than sitting in the navigation graph. A
     * splash that is a nav destination can be reached again with the back button
     * and complicates every back-stack decision after it; an overlay that fades
     * out cannot. If it ever misbehaves it fails on top of a working app.
     */
    @androidx.compose.runtime.Composable()
    public static final void LaunchSequence(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFinished, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * A teardrop centred on [cx],[cy]: a circle of [radius] with a point rising
     * [tip] above it. Rotated 180 degrees this is a map pin.
     */
    private static final androidx.compose.ui.graphics.Path teardrop(float cx, float cy, float radius, float tip) {
        return null;
    }
}