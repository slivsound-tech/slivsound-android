package com.slivsound.navigation

const val GRAPH_DISCOVER = "discover"
const val GRAPH_SOUND = "sound"
const val GRAPH_SETTINGS = "settings"
const val ROUTE_DISCOVER_MAIN = "discover/main"
const val ROUTE_SOUND_MAIN = "sound_main/{soundId}"

const val ROUTE_SETTINGS_MAIN = "settings/main"

fun routeToSoundDetail(soundId: String) = "sound_main/$soundId"