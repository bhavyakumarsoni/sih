package com.geowatershed.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a<\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"PriorityMapScreen", "", "viewModel", "Lcom/geowatershed/app/data/GeoWatershedViewModel;", "onBack", "Lkotlin/Function0;", "onOpenSite", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "app_debug"})
public final class PriorityMapScreenKt {
    
    /**
     * The dedicated full-screen priority map: every geo-tagged site plotted and
     * colour-coded, filterable by zone via the legend.
     *
     * The map is deliberately NOT inside a scrolling container — an interactive
     * map and a vertical scroll fight over the same drag gesture, and the map
     * loses. It takes the free height instead, with the legend pinned below it.
     */
    @androidx.compose.runtime.Composable()
    public static final void PriorityMapScreen(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.GeoWatershedViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenSite, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}