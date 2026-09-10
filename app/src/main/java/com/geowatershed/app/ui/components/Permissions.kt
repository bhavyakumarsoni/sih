package com.geowatershed.app.ui.components

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

class PermissionState(val granted: Boolean, val request: () -> Unit)

/** Tracks whether a single runtime permission is granted, with a launcher ready to request it. */
@Composable
fun rememberPermissionState(permission: String): PermissionState {
    val context = LocalContext.current
    var granted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED,
        )
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        granted = isGranted
    }
    return PermissionState(granted) { launcher.launch(permission) }
}
