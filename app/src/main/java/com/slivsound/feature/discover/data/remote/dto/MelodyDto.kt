package com.slivsound.feature.discover.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MelodiesDto(
    val version: Int,
    @SerialName("updatedAt") val updatedAt: String,
    val type: String,
    val tracks: List<MelodyDto>
)

@Serializable
data class MelodyDto(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("imagePath") val imagePath: String,
    @SerialName("audioPath") val audioPath: String,
    val tags: List<String>
)