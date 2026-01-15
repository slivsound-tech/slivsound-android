package com.slivsound.feature.sound.presentation.repositiry

import kotlinx.coroutines.flow.StateFlow


data class Play(
    var id: String
)

interface PlayRepository {

    val items: StateFlow<List<Play>>
    suspend fun addItem(item: Play)
}
