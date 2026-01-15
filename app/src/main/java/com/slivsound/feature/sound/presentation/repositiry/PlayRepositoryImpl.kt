package com.slivsound.feature.sound.presentation.repositiry

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayRepositoryImpl(

) : PlayRepository {

    private val _items = MutableStateFlow<List<Play>>(emptyList())
    override val items: StateFlow<List<Play>> = _items

    override suspend fun addItem(item: Play) {
        val updated = _items.value.toMutableList().apply {
            add(item)
        }
        _items.value = updated
    }
}