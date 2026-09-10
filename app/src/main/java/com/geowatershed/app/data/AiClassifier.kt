package com.geowatershed.app.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Base64
import com.geowatershed.app.data.model.AiCertainty
import com.geowatershed.app.data.model.ObservationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

/** What the model proposed, before any human has looked at it. */
data class AiSuggestion(
    val observationType: ObservationType,
    val certainty: AiCertainty,
    val rationale: String,
)

/**
 * Tier-1, zero-training image classification: a captured field photo is sent
 * to a vision-capable model and mapped onto the app's EXISTING observation
 * categories. No custom model is trained, because there is no labelled
 * dataset to train one on.
 *
 * Hard boundaries, enforced by where this class is called from rather than by
 * politeness:
 *  - the result never touches the priority score,
 *  - the result never advances an intervention,
 *  - the result is a suggestion until a human confirms or rejects it.
 *
 * Uses HttpURLConnection and org.json, both already in the platform, so this
 * adds no new dependency to the build.
 */
object AiClassifier {

    private const val ENDPOINT = "https://api.anthropic.com/v1/messages"
    private const val ANTHROPIC_VERSION = "2023-06-01"
    private const val MAX_EDGE_PX = 1024
    private const val JPEG_QUALITY = 80
    private const val TIMEOUT_MS = 30_000

    fun hasNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false
        val caps = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    /**
     * Returns the suggestion, or a [Result.failure] carrying a message fit to
     * show a field user. Never throws into the caller.
     */
    suspend fun classify(photoPath: String, apiKey: String, model: String): Result<AiSuggestion> =
        withContext(Dispatchers.IO) {
            runCatching {
                val base64 = encodePhoto(photoPath) ?: error("Photo file could not be read.")
                val body = requestBody(base64, model)
                val text = post(body, apiKey)
                parseSuggestion(text)
            }
        }

    /** Downscales and re-compresses so a field photo is not uploaded at full sensor size. */
    private fun encodePhoto(path: String): String? {
        val file = File(path)
        if (!file.exists()) return null

        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(path, bounds)
        val longestEdge = maxOf(bounds.outWidth, bounds.outHeight).coerceAtLeast(1)

        var sample = 1
        while (longestEdge / sample > MAX_EDGE_PX) sample *= 2

        val bitmap = BitmapFactory.decodeFile(path, BitmapFactory.Options().apply { inSampleSize = sample })
            ?: return null
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, stream)
        bitmap.recycle()
        return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
    }

    private fun requestBody(base64Jpeg: String, model: String): String {
        val categories = ObservationType.entries.joinToString("\n") { "- ${it.name}: ${it.label}" }
        val prompt = """
            You are assisting a watershed field worker in rural India. Classify what this
            geo-tagged field photograph primarily shows into EXACTLY ONE of these categories:

            $categories

            Then state your own certainty as one of: High, Medium, Low.

            Be conservative. If the image is unclear, poorly lit, or shows something the
            categories do not cover, choose Other and say Low. Do not guess to be helpful.
            You are looking at a single point, not a whole area, so do not describe the
            surrounding landscape as if you can see it.

            Reply with ONLY a JSON object, no other text:
            {"category":"<one category name from the list above>","certainty":"High|Medium|Low","rationale":"<one sentence, max 25 words, describing only what is visible>"}
        """.trimIndent()

        val image = JSONObject()
            .put("type", "image")
            .put(
                "source",
                JSONObject()
                    .put("type", "base64")
                    .put("media_type", "image/jpeg")
                    .put("data", base64Jpeg),
            )
        val textPart = JSONObject().put("type", "text").put("text", prompt)
        val content = JSONArray().put(image).put(textPart)
        val message = JSONObject().put("role", "user").put("content", content)

        return JSONObject()
            .put("model", model)
            .put("max_tokens", 300)
            .put("messages", JSONArray().put(message))
            .toString()
    }

    private fun post(body: String, apiKey: String): String {
        val connection = (URL(ENDPOINT).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            doOutput = true
            setRequestProperty("content-type", "application/json")
            setRequestProperty("x-api-key", apiKey)
            setRequestProperty("anthropic-version", ANTHROPIC_VERSION)
        }
        try {
            connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
            val code = connection.responseCode
            val payload = if (code in 200..299) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                val err = connection.errorStream?.bufferedReader()?.use { it.readText() }.orEmpty()
                error(friendlyError(code, err))
            }
            return extractText(payload)
        } finally {
            connection.disconnect()
        }
    }

    private fun friendlyError(code: Int, raw: String): String = when (code) {
        401, 403 -> "The API key was rejected. Check it on the AI Assist settings screen."
        404 -> "The configured model was not found. Check the model id on the AI Assist settings screen."
        429 -> "Rate limited. The capture stays queued and can be retried."
        in 500..599 -> "The service returned an error ($code). The capture stays queued."
        else -> "Request failed ($code). ${raw.take(160)}"
    }

    /** Pulls the assistant's text out of the Messages API envelope. */
    private fun extractText(payload: String): String {
        val content = JSONObject(payload).optJSONArray("content") ?: error("Unexpected response shape.")
        for (i in 0 until content.length()) {
            val part = content.optJSONObject(i) ?: continue
            if (part.optString("type") == "text") return part.optString("text")
        }
        error("No text in the response.")
    }

    private fun parseSuggestion(text: String): AiSuggestion {
        // The model is asked for bare JSON, but tolerate it being wrapped in prose.
        val start = text.indexOf('{')
        val end = text.lastIndexOf('}')
        if (start < 0 || end <= start) error("Could not read the model's reply.")
        val json = JSONObject(text.substring(start, end + 1))

        val category = json.optString("category")
        val type = ObservationType.entries.firstOrNull { it.name.equals(category, ignoreCase = true) }
            ?: ObservationType.entries.firstOrNull { it.label.equals(category, ignoreCase = true) }
            ?: ObservationType.Other

        return AiSuggestion(
            observationType = type,
            certainty = AiCertainty.parse(json.optString("certainty")),
            rationale = json.optString("rationale").ifBlank { "No rationale returned." },
        )
    }
}
