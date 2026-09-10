package com.geowatershed.app.data

/**
 * Governance Mode login. There is no backend yet, so this checks against a
 * fixed set of officer credentials issued out of band by the BDO office —
 * there is deliberately no self-service account creation anywhere in the app.
 */
object GovernanceCredentials {
    private val officers = mapOf(
        "bdo.kolar" to Pair("kolar@2026", "BDO Kolar"),
        "officer1" to Pair("chinnahalli1", "Field Officer · Chinnahalli"),
    )

    /** Returns the officer's display name on success, null on a bad ID/password. */
    fun authenticate(officerId: String, password: String): String? {
        val entry = officers[officerId.trim().lowercase()] ?: return null
        return if (entry.first == password) entry.second else null
    }
}
