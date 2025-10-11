package com.slivsound.feature.discover.data.model

import com.slivsound.core.network.ApiClient
import com.slivsound.feature.discover.domain.SoundModel
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

fun MelodyDto.toDomain(): SoundModel {
    return SoundModel(
        id = id,
        title = name,
        description = description,
        imageUrl = ApiClient.BASE_URL + imagePath,
        audioUrl = ApiClient.BASE_URL + audioPath,
        tags = tags
    )
}

fun MelodiesDto.toDomain(): List<SoundModel> {
    return tracks.map { it.toDomain() }
}
