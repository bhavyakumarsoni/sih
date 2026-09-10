package com.geowatershed.app.ui.screens

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.PhotoSet
import com.geowatershed.app.ui.components.AddPhotoButton
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.PhotoSlotCard
import com.geowatershed.app.ui.components.PrimaryCtaButton
import com.geowatershed.app.ui.components.QuickCameraCaptureOverlay
import com.geowatershed.app.ui.components.rememberPermissionState
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

@Composable
fun MonitoringScreen(
    viewModel: GeoWatershedViewModel,
    interventionId: Long,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val intervention by viewModel.interventionFlow(interventionId).collectAsState(initial = null)
    val photos by viewModel.photosFor(interventionId).collectAsState(initial = emptyList())
    val beforePhotos = remember(photos) { photos.filter { PhotoSet.valueOf(it.photoSet) == PhotoSet.Before } }
    val afterPhotos = remember(photos) { photos.filter { PhotoSet.valueOf(it.photoSet) == PhotoSet.After } }
    val beforeCount = beforePhotos.size
    val afterCount = afterPhotos.size
    val total = beforeCount + afterCount
    val ready = beforeCount >= GeoWatershedViewModel.EVIDENCE_MINIMUM_PER_SET &&
        afterCount >= GeoWatershedViewModel.EVIDENCE_MINIMUM_PER_SET
    val submitted = intervention?.verificationSubmitted == true

    var pendingAddSet by remember { mutableStateOf<PhotoSet?>(null) }
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(GWColors.NeutralBg)) {
            BackHeader(
                title = intervention?.type ?: "Before / After",
                subtitle = intervention?.siteDetail ?: "",
                onBack = onBack,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                EvidenceHeaderCard(
                    note = when {
                        submitted -> "Submitted for verification."
                        ready -> "Minimum evidence met — ready for verification."
                        else -> "Two matched pairs required before verification."
                    },
                    count = if (ready) "$total PHOTOS · MIN MET" else "$total / 4 PHOTOS",
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        PhotoColumnLabel(text = "BEFORE", color = GWColors.Ink600, dotColor = GWColors.Ink500)
                        beforePhotos.forEach { PhotoSlotCard(it) }
                        AddPhotoButton(
                            label = "＋ Add Photo",
                            forSet = PhotoSet.Before,
                            onClick = {
                                if (cameraPermission.granted) pendingAddSet = PhotoSet.Before else cameraPermission.request()
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        PhotoColumnLabel(text = "AFTER", color = GWColors.Green700, dotColor = GWColors.Green700)
                        afterPhotos.forEach { PhotoSlotCard(it) }
                        AddPhotoButton(
                            label = "＋ Add Photo",
                            forSet = PhotoSet.After,
                            onClick = {
                                if (cameraPermission.granted) pendingAddSet = PhotoSet.After else cameraPermission.request()
                            },
                        )
                    }
                }
                PrimaryCtaButton(
                    label = if (submitted) "✓ Submitted for Verification" else "Submit for Verification",
                    onClick = { intervention?.let { viewModel.submitForVerification(it) } },
                    background = GWColors.Green700,
                    height = 64.dp,
                    cornerRadius = 13.dp,
                    enabled = ready && !submitted,
                )
            }
        }

        pendingAddSet?.let { set ->
            QuickCameraCaptureOverlay(
                onCaptured = { file ->
                    viewModel.addPhoto(interventionId, set, file)
                    pendingAddSet = null
                },
                onCancel = { pendingAddSet = null },
            )
        }
    }
}

@Composable
private fun PhotoColumnLabel(text: String, color: Color, dotColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(modifier = Modifier.size(10.dp).background(dotColor, RoundedCornerShape(2.dp)))
        Text(text, style = GWType.badge.copy(fontSize = 12.sp, letterSpacing = 0.12.em), color = color)
    }
}

@Composable
private fun EvidenceHeaderCard(note: String, count: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(14.dp))
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(14.dp))
            .padding(horizontal = 16.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text("MONITORING EVIDENCE", style = GWType.label, color = GWColors.Ink500)
            Text(note, style = GWType.bodySmall.copy(fontSize = 13.sp, lineHeight = 17.sp), color = GWColors.Ink600)
        }
        Text(
            count,
            style = GWType.badge.copy(fontSize = 12.sp),
            color = GWColors.ClayTextDark2,
            modifier = Modifier
                .background(GWColors.Clay100, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 9.dp),
        )
    }
}
