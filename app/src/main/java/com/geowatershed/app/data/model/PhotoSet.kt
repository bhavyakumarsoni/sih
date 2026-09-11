package com.geowatershed.app.data.model

enum class PhotoSet(val note: String) {
    Before("PRE-WORK PHOTO"),
    After("POST-WORK PHOTO"),
    ;

    companion object {
        /** Reads a photo set from a stored string without throwing. */
        fun parse(raw: String?): PhotoSet = entries.firstOrNull { it.name == raw } ?: Before
    }
}
