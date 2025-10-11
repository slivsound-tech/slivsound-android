package com.slivsound.feature.discover.domain


data class SoundModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val audioUrl: String,
    val tags: List<String>
)