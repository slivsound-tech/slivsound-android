package com.slivsound.feature.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.sound.presentation.repositiry.PlayRepository
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.feature.sound.presentation.repositiry.Play
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class State(
    val melodies: List<SoundModel> = emptyList(),
    val sounds: List<SoundModel> = emptyList(),
    val noise: List<SoundModel> = emptyList(),
    val filteredMelodies: List<SoundModel> = emptyList(),
    val filteredSounds: List<SoundModel> = emptyList(),
    val filteredNoise: List<SoundModel> = emptyList(),
    val searchQuery: String = ""
)

sealed interface DiscoverEvent {
    data object LoadAll : DiscoverEvent
    data class Search(val query: String) : DiscoverEvent

//    data class OnSoundSelected(val sound: SoundModel) : DiscoverEvent
}

@KoinViewModel
class DiscoverViewModel(
    private val repository: DiscoverRepository,
    private val playrepository: PlayRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        onEvent(DiscoverEvent.LoadAll)
    }

    fun addItems(sound: Play) {
        viewModelScope.launch {
            playrepository.addItem(sound)
        }
    }

    fun onEvent(event: DiscoverEvent) {
        viewModelScope.launch {
            when (event) {
                DiscoverEvent.LoadAll -> {
                    repository.fetchMelodies().onSuccess { list ->
                        _state.update { it.copy(melodies = list, filteredMelodies = list) }
                    }
                    repository.fetchSounds().onSuccess { list ->
                        _state.update { it.copy(sounds = list, filteredSounds = list) }
                    }
                    repository.getNoiseforSleep().onSuccess { list ->
                        _state.update { it.copy(noise = list, filteredNoise = list) }
                    }
                }

                is DiscoverEvent.Search -> {
                    _state.update { it.copy(searchQuery = event.query) }
                    filterContent(event.query)
                }
            }
        }
    }

    private fun filterContent(query: String) {
        val current = _state.value

        if (query.isBlank()) {
            _state.update {
                it.copy(
                    filteredMelodies = it.melodies,
                    filteredSounds = it.sounds,
                    filteredNoise = it.noise
                )
            }
        } else {
            _state.update {
                it.copy(
                    filteredMelodies = current.melodies.filter {
                        it.title.contains(query, ignoreCase = true)
                    },
                    filteredSounds = current.sounds.filter {
                        it.title.contains(query, ignoreCase = true)
                    },
                    filteredNoise = current.noise.filter {
                        it.title.contains(query, ignoreCase = true)
                    }
                )
            }
        }
    }

    fun onSoundSelected(sound: SoundModel) {
        repository.saveLastSound(sound)
    }
}

