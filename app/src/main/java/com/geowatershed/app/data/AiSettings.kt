package com.geowatershed.app.data

import android.content.Context

/**
 * Runtime configuration for the experimental AI assist.
 *
 * The API key is entered by the operator on the device and stored only in
 * this app's private preferences. It is deliberately NOT compiled into the
 * APK and NOT committed to the repository — an embedded key ships to every
 * device that installs the app and can be extracted from it trivially.
 *
 * The model id is configurable for the same reason a hardcoded one would be
 * a nuisance: model names change, and changing one should not require a
 * rebuild in the middle of a demo.
 */
class AiSettings(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences("geowatershed_ai", Context.MODE_PRIVATE)

    var apiKey: String?
        get() = prefs.getString(KEY_API, null)?.takeIf { it.isNotBlank() }
        set(value) = prefs.edit().putString(KEY_API, value?.trim()).apply()

    var model: String
        get() = prefs.getString(KEY_MODEL, DEFAULT_MODEL) ?: DEFAULT_MODEL
        set(value) = prefs.edit().putString(KEY_MODEL, value.trim().ifBlank { DEFAULT_MODEL }).apply()

    val isConfigured: Boolean get() = apiKey != null

    companion object {
        private const val KEY_API = "api_key"
        private const val KEY_MODEL = "model"

        /**
         * Check this against current model documentation before a demo — model
         * identifiers are versioned and go out of date. It is editable on the
         * settings screen precisely so a stale default is not a blocker.
         */
        const val DEFAULT_MODEL = "claude-sonnet-4-5"
    }
}
