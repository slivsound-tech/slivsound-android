package com.slivsound.feature.onboarding.presentation.question

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

data class OnboardingQuestionState(
    val isLoading: Boolean = false,
    val questionText: String = "How do you want to use Slivsound?"
)

sealed interface OnboardingQuestionEvent {
    data object NextClicked : OnboardingQuestionEvent
    data object BackClicked : OnboardingQuestionEvent
}

sealed interface OnboardingQuestionEffect {
    data class ShowToast(val message: String) : OnboardingQuestionEffect
    data object NavigateNext : OnboardingQuestionEffect
    data object NavigateBack : OnboardingQuestionEffect
}

@KoinViewModel
class OnboardingQuestionViewModel : ViewModel() {

    private val _state = MutableStateFlow(OnboardingQuestionState())
    val state: StateFlow<OnboardingQuestionState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingQuestionEffect>(extraBufferCapacity = 1)
    val effects: Flow<OnboardingQuestionEffect> = _effects.asSharedFlow()

    fun onEvent(event: OnboardingQuestionEvent) {
        when (event) {
            OnboardingQuestionEvent.NextClicked -> {
                _effects.tryEmit(OnboardingQuestionEffect.NavigateNext)
            }

            OnboardingQuestionEvent.BackClicked -> {
                _effects.tryEmit(OnboardingQuestionEffect.NavigateBack)
            }
        }
    }
}
