package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001aB\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001ap\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"AiActionButton", "", "label", "", "onClick", "Lkotlin/Function0;", "enabled", "", "AiDecisionButton", "textColor", "Landroidx/compose/ui/graphics/Color;", "background", "modifier", "Landroidx/compose/ui/Modifier;", "AiDecisionButton-1wkBAMs", "(Ljava/lang/String;JJLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;)V", "AiSuggestionCard", "capture", "Lcom/geowatershed/app/data/db/CaptureEntity;", "isConfigured", "busy", "onQueue", "onConfirm", "onReject", "onRetry", "onOpenSettings", "app_debug"})
public final class AiSuggestionCardKt {
    
    /**
     * The experimental AI assist panel.
     *
     * Three things this card is built to never do:
     * - present a suggestion as a finding (it is always labelled a suggestion,
     *   always experimental, and always carries a confirm/reject pair),
     * - imply it changed the priority score (it says so explicitly),
     * - imply a single photo characterises the surrounding area.
     *
     * GIS supplies the spatial evidence, AI assists interpretation, the field
     * user decides. This card is the third of those, made visible.
     */
    @androidx.compose.runtime.Composable()
    public static final void AiSuggestionCard(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.CaptureEntity capture, boolean isConfigured, boolean busy, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onQueue, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReject, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSettings, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AiActionButton(java.lang.String label, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, boolean enabled) {
    }
}