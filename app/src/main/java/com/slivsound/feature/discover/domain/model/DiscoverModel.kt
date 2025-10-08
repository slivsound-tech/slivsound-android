package com.slivsound.feature.discover.domain.model


data class Melody(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val audioUrl: String,
    val tags: List<String>
)