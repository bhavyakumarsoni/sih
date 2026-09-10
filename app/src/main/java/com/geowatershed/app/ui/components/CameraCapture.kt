package com.geowatershed.app.ui.components

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.io.File

/** Live CameraX preview bound to the current lifecycle; hands the bound [ImageCapture] use case back up once ready. */
@Composable
fun CameraXPreview(onImageCaptureReady: (ImageCapture) -> Unit, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    val imageCapture = ImageCapture.Builder().build()
                    runCatching {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageCapture,
                        )
                        onImageCaptureReady(imageCapture)
                    }
                },
                ContextCompat.getMainExecutor(ctx),
            )
            previewView
        },
    )
}

/** Captures a photo to app-private storage and reports the resulting file (or null on failure). */
fun capturePhotoTo(context: Context, imageCapture: ImageCapture, onResult: (File?) -> Unit) {
    val photosDir = File(context.filesDir, "photos").apply { mkdirs() }
    val photoFile = File(photosDir, "capture_${System.currentTimeMillis()}.jpg")
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onResult(photoFile)
            }

            override fun onError(exception: ImageCaptureException) {
                onResult(null)
            }
        },
    )
}
