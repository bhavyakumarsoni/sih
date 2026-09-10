package com.geowatershed.app.data.model

import androidx.compose.ui.graphics.Color

/**
 * The five map zones from the design mock's ZONES table, with its exact
 * colour values. These are the only five states a site pin can be in.
 *
 * A zone is a function of two things that are actually known: the site's
 * priority score, and how far the work at that site has genuinely
 * progressed. A zone is never derived from missing data — a site with no
 * GIS coverage is not thereby "low priority", and absence of an
 * intervention is not evidence that none is needed.
 */
enum class PriorityZone(
    val key: String,
    val label: String,
    val sub: String,
    val short: String,
    val color: Color,
    val ink: Color,
) {
    Red("red", "High priority", "act now · overdue", "HIGH", Color(0xFFB3261E), Color.White),
    Orange("orange", "Can be delayed", "plan next quarter", "LATER", Color(0xFFE4671B), Color.White),
    Yellow("yellow", "Work in progress", "under construction", "WIP", Color(0xFFF2C230), Color(0xFF3A2A02)),
    Green("green", "Work completed", "verified evidence", "DONE", Color(0xFF1F5C46), Color.White),
    Blue("blue", "Due for monitoring", "revisit this week", "MONI", Color(0xFF2C5F7A), Color.White),
}
