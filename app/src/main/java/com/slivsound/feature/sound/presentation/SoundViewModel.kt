package com.slivsound.feature.sound.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.feature.sound.presentation.repositiry.Play
import com.slivsound.feature.sound.presentation.repositiry.PlayRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


sealed class SoundEvent {

    object OnPlayClick : SoundEvent()
    data class LoadById(val id: String) : SoundEvent()
    object LoadLast : SoundEvent()
    object OnShareClick : SoundEvent()
    object OnAddClick : SoundEvent()
}

sealed class SoundEffect {
    data class ShareSound(
        val id: String,
        val imageUrl: String,
        val title: String,
        val url: String
    ) : SoundEffect()

    object NavigateToDiscover : SoundEffect()
}

sealed class State {
    object Loading : State()
    data class Content(
        val mix: List<Play>,
        val mixSounds: List<SoundModel>,
        val allSounds: List<SoundModel>
    ) : State()

    data class Error(val message: String) : State()
}


@KoinViewModel
class SoundViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: DiscoverRepository,
    private val playRepository: PlayRepository,
) : ViewModel() {
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying
    private val _effect = MutableSharedFlow<SoundEffect>()
    val effect = _effect.asSharedFlow()
    private suspend fun sendEffect(effect: SoundEffect) {
        _effect.emit(effect)
    }

    private val _selectedSound = MutableStateFlow<SoundModel?>(null)
    val selectedSound: StateFlow<SoundModel?> = _selectedSound
    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState

    init {

        val soundId: String? = savedStateHandle["soundId"]
        if (soundId != null) {
            onEvent(SoundEvent.LoadById(soundId))
        } else {
            onEvent(SoundEvent.LoadLast)
        }
        loadAllSounds()
        observeMix()
    }

    private fun buildMixSounds(
        mix: List<Play>,
        allSounds: List<SoundModel>
    ): List<SoundModel> {
        val soundMap = allSounds.associateBy { it.id }

        return mix.mapNotNull { play ->
            soundMap[play.id]
        }
    }

    private fun loadAllSounds() {
        viewModelScope.launch {
            repository.fetchSounds()
                .onSuccess { sounds ->
                    val currentMix = (playRepository.items.value)
                    val mixSounds = buildMixSounds(currentMix, sounds)

                    _uiState.value = State.Content(
                        mix = currentMix,
                        mixSounds = mixSounds,
                        allSounds = sounds
                    )
                }
                .onFailure {
                    _uiState.value = State.Error("Ошибка загрузки музыки")
                }
        }
    }

    private fun observeMix() {
        viewModelScope.launch {
            playRepository.items.collect { mix ->
                val current = _uiState.value
                if (current is State.Content) {
                    val mixSounds = buildMixSounds(mix, current.allSounds)

                    _uiState.value = current.copy(
                        mix = mix,
                        mixSounds = mixSounds
                    )
                    playRepository.playEffects(mix)
                }
            }
        }
    }

    private fun onShareClick() {
        viewModelScope.launch {
            val sound = selectedSound.value ?: return@launch

            _effect.emit(
                SoundEffect.ShareSound(
                    id = sound.id,
                    imageUrl = sound.imageUrl,
                    title = sound.title,
                    url = sound.audioUrl
                )
            )
        }
    }

    private fun togglePlay() {
        _isPlaying.value = !_isPlaying.value

        if (_isPlaying.value) {
            playRepository.playAll()
        } else {
            playRepository.pauseAll()
        }
    }

    fun onEffectAdd(play: Play) {
        viewModelScope.launch {
            playRepository.addItem(play)

            if (_isPlaying.value) {
                playRepository.playEffects(listOf(play))
            }
        }
    }

    fun addEffectById(soundId: String) {
        viewModelScope.launch {
            val sound = repository.getSoundById(soundId) ?: return@launch

            onEffectAdd(
                Play(
                    id = sound.id,
                    audioUrl = sound.audioUrl
                )
            )
        }
    }


    fun onEvent(event: SoundEvent) {
        when (event) {
            is SoundEvent.OnPlayClick -> {
                togglePlay()
            }

            is SoundEvent.LoadById -> {
                loadSoundById(event.id)
            }

            is
            SoundEvent.LoadLast -> {
                loadLastSound()
            }

            is
            SoundEvent.OnShareClick -> {
                onShareClick()
            }

            is SoundEvent.OnAddClick -> {
                viewModelScope.launch {
                    sendEffect(SoundEffect.NavigateToDiscover)
                }
            }
        }
    }

    private fun loadSoundById(id: String) {
        viewModelScope.launch {
            val sound = repository.getSoundById(id)

            if (sound != null) {
                _selectedSound.value = sound
                repository.saveLastSound(sound)
            } else {
                _uiState.value = State.Error("Sound not found")
            }
        }
    }


    private fun loadLastSound() {
        val sound = repository.getLastSound()

        if (sound != null) {
            _selectedSound.value = sound
        } else {
            _uiState.value = State.Error("Выберите мелодию на экране Discover")
        }
    }
}
