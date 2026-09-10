package com.geowatershed.app.ui.components

import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.PlexMono
import com.geowatershed.app.ui.theme.PlexSans

/** Live CameraX viewfinder with the corner-bracket framing chrome and a shutter button. */
@Composable
fun LiveCameraViewfinder(
    onImageCaptureReady: (ImageCapture) -> Unit,
    onShutterClick: () -> Unit,
    photoCaptured: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth().height(250.dp)) {
        CameraXPreview(onImageCaptureReady = onImageCaptureReady, modifier = Modifier.fillMaxWidth().height(250.dp))
        ViewfinderChrome(onShutterClick = onShutterClick)
        if (photoCaptured) {
            Text(
                "✓ PHOTO CAPTURED — tap shutter again to retake",
                color = Color.White,
                fontFamily = PlexSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
                    .background(GWColors.Green700.copy(alpha = 0.9f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            )
        }
    }
}

/** Shown instead of the viewfinder when camera permission hasn't been granted yet. */
@Composable
fun CameraPermissionPlaceholder(onRequestPermission: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(GWColors.ViewfinderDark1)
            .clickable(onClick = onRequestPermission),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Camera access needed\ntap to grant permission",
            color = Color.White.copy(alpha = 0.85f),
            fontFamily = PlexMono,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            letterSpacing = 0.06.em,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BoxScope.ViewfinderChrome(onShutterClick: () -> Unit) {
    val bracketColor = Color.White.copy(alpha = 0.6f)
    ViewfinderCorner(Alignment.TopStart, bracketColor)
    ViewfinderCorner(Alignment.TopEnd, bracketColor)
    ViewfinderCorner(Alignment.BottomStart, bracketColor)
    ViewfinderCorner(Alignment.BottomEnd, bracketColor)
    Box(
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(end = 14.dp)
            .size(64.dp)
            .background(GWColors.NeutralBg.copy(alpha = 0.92f), CircleShape)
            .clickable(onClick = onShutterClick),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.size(44.dp).background(GWColors.Clay600, CircleShape))
    }
}

@Composable
private fun BoxScope.ViewfinderCorner(alignment: Alignment, color: Color) {
    val isTop = alignment == Alignment.TopStart || alignment == Alignment.TopEnd
    val isStart = alignment == Alignment.TopStart || alignment == Alignment.BottomStart
    Canvas(
        modifier = Modifier
            .align(alignment)
            .padding(14.dp)
            .size(26.dp),
    ) {
        val strokeWidth = 3.dp.toPx()
        val half = strokeWidth / 2
        val verticalX = if (isStart) half else size.width - half
        drawLine(color, Offset(verticalX, 0f), Offset(verticalX, size.height), strokeWidth)
        val horizontalY = if (isTop) half else size.height - half
        drawLine(color, Offset(0f, horizontalY), Offset(size.width, horizontalY), strokeWidth)
    }
}
