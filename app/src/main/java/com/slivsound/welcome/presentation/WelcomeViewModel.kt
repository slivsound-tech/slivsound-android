package com.slivsound.welcome.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.featurename.domain.NameRepository
import com.slivsound.featurename.presentation.NameEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class WelcomeState(
    val name: String = "Example",
    val isLoading: Boolean = false,
)

sealed interface WelcomeEvent {
    data object SetupExample : WelcomeEvent
    data class SetExample(val example: String) : WelcomeEvent
}

sealed interface WelcomeEffect {
    data class ShowToast(val message: String) : WelcomeEffect
    data class ShowError(val message: String) : WelcomeEffect
}

@KoinViewModel
class WelcomeViewModel(
    private val repository: WelcomeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(WelcomeState())
    val state: StateFlow<WelcomeState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<WelcomeEffect>(extraBufferCapacity = 1)
    val effects: Flow<WelcomeEffect> = _effects.asSharedFlow()

    init {
        onEvent(WelcomeEvent.SetupExample)
    }

    fun onEvent(event: WelcomeEvent) {
        when (event) {
            NameEvent.SetupExample -> {
                _state.update { it.copy(isLoading = true) }
                setupExample()
            }

            is WelcomeEvent.SetExample -> {
                viewModelScope.launch {
                    _effects.emit(WelcomeEffect.ShowToast(event.example))
                }
            }
        }
    }

    private fun setupExample() {
        val welcome = repository.getWelcome()
        _state.update { it.copy(name = welcome, isLoading = false) }
    }
}