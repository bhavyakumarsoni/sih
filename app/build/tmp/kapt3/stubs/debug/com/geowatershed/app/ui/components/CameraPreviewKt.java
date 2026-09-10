package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a<\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u001a\u0010\r\u001a\u00020\u0001*\u00020\u000e2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a&\u0010\u000f\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0016"}, d2 = {"CameraPermissionPlaceholder", "", "onRequestPermission", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "LiveCameraViewfinder", "onImageCaptureReady", "Lkotlin/Function1;", "Landroidx/camera/core/ImageCapture;", "onShutterClick", "photoCaptured", "", "ViewfinderChrome", "Landroidx/compose/foundation/layout/BoxScope;", "ViewfinderCorner", "alignment", "Landroidx/compose/ui/Alignment;", "color", "Landroidx/compose/ui/graphics/Color;", "ViewfinderCorner-mxwnekA", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/ui/Alignment;J)V", "app_debug"})
public final class CameraPreviewKt {
    
    /**
     * Live CameraX viewfinder with the corner-bracket framing chrome and a shutter button.
     */
    @androidx.compose.runtime.Composable()
    public static final void LiveCameraViewfinder(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.camera.core.ImageCapture, kotlin.Unit> onImageCaptureReady, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onShutterClick, boolean photoCaptured, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Shown instead of the viewfinder when camera permission hasn't been granted yet.
     */
    @androidx.compose.runtime.Composable()
    public static final void CameraPermissionPlaceholder(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermission, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ViewfinderChrome(androidx.compose.foundation.layout.BoxScope $this$ViewfinderChrome, kotlin.jvm.functions.Function0<kotlin.Unit> onShutterClick) {
    }
}