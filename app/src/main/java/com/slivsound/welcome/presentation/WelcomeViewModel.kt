package com.slivsound.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.welcome.domain.WelcomeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WelcomeViewModel(
    private val repository: WelcomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeState())
    val state: StateFlow<WelcomeState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<WelcomeEffect>(extraBufferCapacity = 1)
    val effects: Flow<WelcomeEffect> = _effects.asSharedFlow()

    init {
        onEvent(WelcomeEvent.SetupWelcome)
    }

    fun onEvent(event: WelcomeEvent) {
        when(event) {
            WelcomeEvent.SetupWelcome -> {
                _state.update { it.copy(isLoading = true) }
                setupWelcome()
            }
            is WelcomeEvent.SetWelcome -> {
                viewModelScope.launch {
                    _effects.emit(WelcomeEffect.ShowToast(event.message))
                }
            }
        }
    }

    private fun setupWelcome() {
        val message = repository.getWelcomeMessage()
        _state.update { it.copy(message = message, isLoading = false) }
    }
}
