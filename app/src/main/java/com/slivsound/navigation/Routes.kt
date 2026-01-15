package com.slivsound.navigation

const val GRAPH_DISCOVER ="discover?mode=default"

const val ROUTE_DISCOVER_S = "discover_main?mode={mode}"
const val GRAPH_SOUND = "sound"
const val GRAPH_SETTINGS = "settings"
const val ROUTE_DISCOVER_MAIN = "discover_main?mode={mode}"
const val ROUTE_SOUND_MAIN = "sound_main/{soundId}"

const val ROUTE_SETTINGS_MAIN = "settings/main"

fun routeToSoundDetail(soundId: String) = "sound_main/$soundId"