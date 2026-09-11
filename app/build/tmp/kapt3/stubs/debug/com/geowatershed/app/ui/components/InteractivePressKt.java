package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001aH\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"PRESSED_SCALE", "", "interactivePress", "Landroidx/compose/ui/Modifier;", "onClick", "Lkotlin/Function0;", "", "enabled", "", "isAuthorized", "onBlockedTap", "onClickLabel", "", "app_debug"})
public final class InteractivePressKt {
    private static final float PRESSED_SCALE = 0.95F;
    
    /**
     * Press physics for every button in the app: a spring compression to 95% on
     * touch, a light haptic, and an organic settle back to 100% on release.
     *
     * It also handles the blocked case. When [isAuthorized] is false the press
     * does NOT run [onClick] — it fires a heavier haptic and shakes the control
     * horizontally, so a field user who taps an officer-only action gets an
     * immediate physical answer instead of silence.
     *
     * Two deliberate choices worth knowing about:
     *
     * 1. A blocked control keeps its permanent "Officer approval required" label.
     *   The shake CONFIRMS a restriction the user could already read; it does
     *   not reveal one that was hidden until they tapped. A button that looks
     *   available until you try it is the same broken trust in a nicer coat.
     *
     * 2. The blocked state is NOT drawn in the dashed treatment. Dashed means
     *   "we have no data for this" throughout this app, and permission-denied is
     *   a different claim — the data exists, you are not the one who may act on
     *   it. Overloading the one honest signal would cost more than it buys.
     *
     * Placement matters: apply this FIRST in a modifier chain, before any
     * `background`/`border`, or the scale will not include them — it works
     * through a graphics layer, which only affects drawing that comes after it.
     */
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.Modifier interactivePress(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier $this$interactivePress, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, boolean enabled, boolean isAuthorized, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBlockedTap, @org.jetbrains.annotations.Nullable()
    java.lang.String onClickLabel) {
        return null;
    }
}