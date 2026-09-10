package com.geowatershed.app.data;

/**
 * Governance Mode login. There is no backend yet, so this checks against a
 * fixed set of officer credentials issued out of band by the BDO office —
 * there is deliberately no self-service account creation anywhere in the app.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005R&\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/geowatershed/app/data/GovernanceCredentials;", "", "()V", "officers", "", "", "Lkotlin/Pair;", "authenticate", "officerId", "password", "app_debug"})
public final class GovernanceCredentials {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, kotlin.Pair<java.lang.String, java.lang.String>> officers = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.GovernanceCredentials INSTANCE = null;
    
    private GovernanceCredentials() {
        super();
    }
    
    /**
     * Returns the officer's display name on success, null on a bad ID/password.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String authenticate(@org.jetbrains.annotations.NotNull()
    java.lang.String officerId, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
}