package com.geowatershed.app.ui.components

import androidx.camera.core.ImageCapture
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File

/**
 * Full-screen CameraX capture used for ad-hoc photo capture (e.g. adding a
 * single before/after monitoring photo) rather than the persistent preview
 * on the main Capture screen.
 */
@Composable
fun QuickCameraCaptureOverlay(onCaptured: (File?) -> Unit, onCancel: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    Box(modifier = modifier.fillMaxSize().background(Color.Black)) {
        CameraXPreview(onImageCaptureReady = { imageCapture = it }, modifier = Modifier.fillMaxSize())

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.85f), CircleShape)
                .clickable(onClick = onCancel),
            contentAlignment = Alignment.Center,
        ) {
            Text("✕", color = Color.Black)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .size(72.dp)
                .background(Color.White, CircleShape)
                .clickable(enabled = imageCapture != null) {
                    imageCapture?.let { capture -> capturePhotoTo(context, capture, onCaptured) }
                },
        )
    }
}
