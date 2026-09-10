package com.geowatershed.app.ui.screens

import android.Manifest
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.geowatershed.app.data.GeoWatershedViewModel
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.ui.components.BackHeader
import com.geowatershed.app.ui.components.CameraPermissionPlaceholder
import com.geowatershed.app.ui.components.DescriptionField
import com.geowatershed.app.ui.components.GpsCard
import com.geowatershed.app.ui.components.LiveCameraViewfinder
import com.geowatershed.app.ui.components.ObservationTypeList
import com.geowatershed.app.ui.components.PrimaryCtaButton
import com.geowatershed.app.ui.components.SectionLabelRow
import com.geowatershed.app.ui.components.SectionLabelWithSuffix
import com.geowatershed.app.ui.components.capturePhotoTo
import com.geowatershed.app.ui.components.rememberPermissionState
import com.geowatershed.app.ui.theme.GWColors
import kotlinx.coroutines.launch

@Composable
fun CaptureScreen(
    viewModel: GeoWatershedViewModel,
    onBack: () -> Unit,
    onSaveAndAnalyze: (captureId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    val locationPermission = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (!cameraPermission.granted) cameraPermission.request()
        if (!locationPermission.granted) locationPermission.request()
    }
    LaunchedEffect(locationPermission.granted) {
        if (locationPermission.granted && viewModel.gpsFix == null && !viewModel.gpsSearching) {
            viewModel.refreshLocation()
        }
    }

    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var saving by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize().background(GWColors.NeutralBg)) {
        BackHeader(
            title = "Capture Field Image",
            subtitle = "${viewModel.watershedName.removeSuffix(" Watershed")} · new record",
            onBack = onBack,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            if (cameraPermission.granted) {
                LiveCameraViewfinder(
                    onImageCaptureReady = { imageCapture = it },
                    onShutterClick = {
                        imageCapture?.let { capture ->
                            capturePhotoTo(context, capture) { file -> viewModel.onPhotoCaptured(file) }
                        }
                    },
                    photoCaptured = viewModel.capturedPhotoPath != null,
                )
            } else {
                CameraPermissionPlaceholder(onRequestPermission = cameraPermission.request)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .padding(top = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                GpsCard(
                    fix = viewModel.gpsFix,
                    searching = viewModel.gpsSearching,
                    permissionGranted = locationPermission.granted,
                    onClick = {
                        if (locationPermission.granted) viewModel.refreshLocation() else locationPermission.request()
                    },
                )

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    SectionLabelRow("OBSERVATION TYPE", "REQUIRED", GWColors.Clay600)
                    ObservationTypeList(
                        options = ObservationType.entries,
                        selected = viewModel.selectedObservation,
                        onSelect = viewModel::selectObservation,
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    SectionLabelWithSuffix("DESCRIPTION", "· OPTIONAL", GWColors.Ink300)
                    DescriptionField(
                        value = viewModel.description,
                        onValueChange = viewModel::updateDescription,
                        charLimit = 280,
                        placeholder = "Gully forming along the north field boundary…",
                    )
                }

                PrimaryCtaButton(
                    label = if (saving) "Saving…" else "Save & Analyze",
                    onClick = {
                        saving = true
                        scope.launch {
                            val id = viewModel.saveAndAnalyze()
                            saving = false
                            onSaveAndAnalyze(id)
                        }
                    },
                    showArrow = true,
                    enabled = !saving,
                )
            }
        }
    }
}
