package com.geowatershed.app.data;

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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u000eR$\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u0016\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/geowatershed/app/data/AiSettings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "apiKey", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "isConfigured", "", "()Z", "model", "getModel", "setModel", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "Companion", "app_debug"})
public final class AiSettings {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_API = "api_key";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MODEL = "model";
    
    /**
     * Check this against current model documentation before a demo — model
     * identifiers are versioned and go out of date. It is editable on the
     * settings screen precisely so a stale default is not a blocker.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_MODEL = "claude-sonnet-4-5";
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.AiSettings.Companion Companion = null;
    
    public AiSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getApiKey() {
        return null;
    }
    
    public final void setApiKey(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModel() {
        return null;
    }
    
    public final void setModel(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean isConfigured() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/geowatershed/app/data/AiSettings$Companion;", "", "()V", "DEFAULT_MODEL", "", "KEY_API", "KEY_MODEL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}