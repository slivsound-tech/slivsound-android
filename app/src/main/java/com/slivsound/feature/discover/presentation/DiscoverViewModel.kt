package com.slivsound.feature.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class State(
    val melodies: List<SoundModel> = emptyList(),
    val sounds: List<SoundModel> = emptyList(),
    val noise: List<SoundModel> = emptyList(),
)

sealed interface DiscoverEvent {
    data object LoadAll : DiscoverEvent
    data object FetchMelodies : DiscoverEvent
    data object FetchSounds : DiscoverEvent
    data object FetchNoise : DiscoverEvent

}

@KoinViewModel
class DiscoverViewModel(
    private val repository: DiscoverRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        onEvent(DiscoverEvent.LoadAll)
    }

    fun onEvent(event: DiscoverEvent) {
        viewModelScope.launch {
            when (event) {
                DiscoverEvent.FetchMelodies -> {
                    repository.fetchMelodies()
                        .onSuccess { list ->
                            _state.update { it.copy(melodies = list) }
                        }
                }

                is DiscoverEvent.FetchSounds -> {
                    repository.fetchSounds()
                        .onSuccess { list ->
                            _state.update { it.copy(noise = list) }
                        }
                }

                is DiscoverEvent.FetchNoise -> {
                    repository.getNoiseforSleep()
                        .onSuccess { list ->
                            _state.update { it.copy(sounds = list) }
                        }
                }

                is DiscoverEvent.LoadAll -> {
                    repository.fetchMelodies().onSuccess { list ->
                        _state.update { it.copy(melodies = list) }
                    }
                    repository.fetchSounds().onSuccess { list ->
                        _state.update { it.copy(sounds = list) }
                    }
                    repository.getNoiseforSleep().onSuccess { list ->
                        _state.update { it.copy(noise = list) }
                    }
                }
            }
        }
    }
}
