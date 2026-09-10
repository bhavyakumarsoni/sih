package com.geowatershed.app.data;

/**
 * Tier-1, zero-training image classification: a captured field photo is sent
 * to a vision-capable model and mapped onto the app's EXISTING observation
 * categories. No custom model is trained, because there is no labelled
 * dataset to train one on.
 *
 * Hard boundaries, enforced by where this class is called from rather than by
 * politeness:
 * - the result never touches the priority score,
 * - the result never advances an intervention,
 * - the result is a suggestion until a human confirms or rejects it.
 *
 * Uses HttpURLConnection and org.json, both already in the platform, so this
 * adds no new dependency to the build.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J4\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J\u0018\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006#"}, d2 = {"Lcom/geowatershed/app/data/AiClassifier;", "", "()V", "ANTHROPIC_VERSION", "", "ENDPOINT", "JPEG_QUALITY", "", "MAX_EDGE_PX", "TIMEOUT_MS", "classify", "Lkotlin/Result;", "Lcom/geowatershed/app/data/AiSuggestion;", "photoPath", "apiKey", "model", "classify-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodePhoto", "path", "extractText", "payload", "friendlyError", "code", "raw", "hasNetwork", "", "context", "Landroid/content/Context;", "parseSuggestion", "text", "post", "body", "requestBody", "base64Jpeg", "app_debug"})
public final class AiClassifier {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ENDPOINT = "https://api.anthropic.com/v1/messages";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ANTHROPIC_VERSION = "2023-06-01";
    private static final int MAX_EDGE_PX = 1024;
    private static final int JPEG_QUALITY = 80;
    private static final int TIMEOUT_MS = 30000;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.AiClassifier INSTANCE = null;
    
    private AiClassifier() {
        super();
    }
    
    public final boolean hasNetwork(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Downscales and re-compresses so a field photo is not uploaded at full sensor size.
     */
    private final java.lang.String encodePhoto(java.lang.String path) {
        return null;
    }
    
    private final java.lang.String requestBody(java.lang.String base64Jpeg, java.lang.String model) {
        return null;
    }
    
    private final java.lang.String post(java.lang.String body, java.lang.String apiKey) {
        return null;
    }
    
    private final java.lang.String friendlyError(int code, java.lang.String raw) {
        return null;
    }
    
    /**
     * Pulls the assistant's text out of the Messages API envelope.
     */
    private final java.lang.String extractText(java.lang.String payload) {
        return null;
    }
    
    private final com.geowatershed.app.data.AiSuggestion parseSuggestion(java.lang.String text) {
        return null;
    }
}