package com.slivsound.app.featurename.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.app.featurename.domain.NameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class NameState(
    val name: String = "Example",
    val isLoading: Boolean = false,
)

sealed interface NameEvent {
    data object SetupExample : NameEvent
    data class SetExample(val example: String) : NameEvent
}

sealed interface NameEffect {
    data class ShowToast(val message: String) : NameEffect
    data class ShowError(val message: String) : NameEffect
}

@KoinViewModel
class NameViewModel(
    private val repository: NameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NameState())
    val state: StateFlow<NameState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<NameEffect>(extraBufferCapacity = 1)
    val effects: Flow<NameEffect> = _effects.asSharedFlow()

    init {
        onEvent(NameEvent.SetupExample)
    }

    fun onEvent(event: NameEvent) {
        when (event) {
            NameEvent.SetupExample -> {
                _state.update { it.copy(isLoading = true) }
                setupExample()
            }

            is NameEvent.SetExample -> {
                viewModelScope.launch {
                    _effects.emit(NameEffect.ShowToast(event.example))
                }
            }
        }
    }

    private fun setupExample() {
        val name = repository.getName()
        _state.update { it.copy(name = name, isLoading = false) }
    }
}