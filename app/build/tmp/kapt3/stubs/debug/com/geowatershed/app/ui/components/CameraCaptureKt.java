package com.geowatershed.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a,\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00010\u0003\u00a8\u0006\r"}, d2 = {"CameraXPreview", "", "onImageCaptureReady", "Lkotlin/Function1;", "Landroidx/camera/core/ImageCapture;", "modifier", "Landroidx/compose/ui/Modifier;", "capturePhotoTo", "context", "Landroid/content/Context;", "imageCapture", "onResult", "Ljava/io/File;", "app_debug"})
public final class CameraCaptureKt {
    
    /**
     * Live CameraX preview bound to the current lifecycle; hands the bound [ImageCapture] use case back up once ready.
     */
    @androidx.compose.runtime.Composable()
    public static final void CameraXPreview(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.camera.core.ImageCapture, kotlin.Unit> onImageCaptureReady, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Captures a photo to app-private storage and reports the resulting file (or null on failure).
     */
    public static final void capturePhotoTo(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageCapture imageCapture, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> onResult) {
    }
}