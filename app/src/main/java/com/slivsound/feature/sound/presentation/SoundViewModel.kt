package com.slivsound.feature.sound.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


sealed class SoundEvent {
    data class LoadById(val id: String) : SoundEvent()
    object LoadLast : SoundEvent()
}

sealed class State {
    object Loading : State()
    data class Success(val sound: SoundModel) : State()
    data class Error(val message: String) : State()
}


@KoinViewModel
class SoundViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: DiscoverRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState

    init {
        val soundId: String? = savedStateHandle["soundId"]
        if (soundId != null) {
            onEvent(SoundEvent.LoadById(soundId))
        } else {
            onEvent(SoundEvent.LoadLast)
        }
    }

    fun onEvent(event: SoundEvent) {
        when (event) {

            is SoundEvent.LoadById -> {
                loadSoundById(event.id)
            }

            SoundEvent.LoadLast -> {
                loadLastSound()
            }
        }
    }

    private fun loadSoundById(id: String) {
        viewModelScope.launch {
            _uiState.value = State.Loading

            val sound = repository.getSoundById(id)

            if (sound != null) {
                _uiState.value = State.Success(sound)
                repository.saveLastSound(sound)
            } else {
                _uiState.value = State.Error("Sound not found")
            }
        }
    }

    private fun loadLastSound() {
        val sound = repository.getLastSound()

        if (sound != null) {
            _uiState.value = State.Success(sound)
        } else {
            _uiState.value = State.Error("Выберите мелодию на экране Discover")
        }
    }
}