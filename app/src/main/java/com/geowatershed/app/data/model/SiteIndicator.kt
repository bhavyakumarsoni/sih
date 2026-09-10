package com.geowatershed.app.data.model

/**
 * A single site indicator row. [value] is null when the data source has no
 * record for this parcel yet — the "honest gap" state — never a placeholder
 * zero or an error.
 */
data class SiteIndicator(
    val name: String,
    val sub: String,
    val value: String?,
) {
    val isAvailable: Boolean get() = value != null
}
