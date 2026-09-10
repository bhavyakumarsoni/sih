package com.geowatershed.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.geowatershed.app.ui.navigation.GWNavHost
import com.geowatershed.app.ui.theme.GeoWatershedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoWatershedTheme {
                GWNavHost()
            }
        }
    }
}
