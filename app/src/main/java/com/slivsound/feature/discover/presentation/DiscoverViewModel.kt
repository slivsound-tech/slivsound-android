package com.slivsound.feature.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.feature.discover.domain.DiscoverRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DiscoverViewModel(
    private val repository: DiscoverRepository
) : ViewModel() {


    private val _state = MutableStateFlow<List<SoundModel>>(emptyList())
    val state: StateFlow<List<SoundModel>> = _state
    private val _state1 = MutableStateFlow<List<SoundModel>>(emptyList())
    val state1: StateFlow<List<SoundModel>> = _state1
    private val _state2 = MutableStateFlow<List<SoundModel>>(emptyList())
    val state2: StateFlow<List<SoundModel>> = _state2

    init {
        load()
    }

    fun load() = viewModelScope.launch {
        repository.fetchMelodies()
            .onSuccess { _state.value = it }
        repository.fetchSounds()
            .onSuccess { _state1.value = it }
        repository.getNoiseforSleep()
            .onSuccess { _state2.value = it }
    }
}