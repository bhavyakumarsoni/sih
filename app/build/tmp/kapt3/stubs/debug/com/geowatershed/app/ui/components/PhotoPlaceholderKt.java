package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a\u001a\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a,\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"AddPhotoButton", "", "label", "", "forSet", "Lcom/geowatershed/app/data/model/PhotoSet;", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "MissingPhotoSlot", "PhotoSlotCard", "photo", "Lcom/geowatershed/app/data/db/PhotoEvidenceEntity;", "StripedPlaceholder", "stripeColor", "Landroidx/compose/ui/graphics/Color;", "backgroundColor", "StripedPlaceholder-jxsXWHM", "(JJLandroidx/compose/ui/Modifier;)V", "app_debug"})
public final class PhotoPlaceholderKt {
    
    @androidx.compose.runtime.Composable()
    public static final void PhotoSlotCard(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.PhotoEvidenceEntity photo, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddPhotoButton(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.PhotoSet forSet, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Stands in for the missing half of a before/after pair. Deliberately the
     * same dashed, neutral treatment used for unavailable indicators — an
     * un-taken photo is missing evidence, not a failure, and never shows red.
     */
    @androidx.compose.runtime.Composable()
    public static final void MissingPhotoSlot(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.PhotoSet forSet, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}