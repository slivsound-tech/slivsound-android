package com.slivsound.feature.onboarding.presentation.welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

data class OnboardingWelcomeState(
    val isLoading: Boolean = false
)

sealed interface OnboardingWelcomeEvent

sealed interface OnboardingWelcomeEffect

@KoinViewModel
class OnboardingWelcomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(OnboardingWelcomeState())
    val state: StateFlow<OnboardingWelcomeState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingWelcomeEffect>(extraBufferCapacity = 1)
    val effects: Flow<OnboardingWelcomeEffect> = _effects.asSharedFlow()

    fun onEvent(event: OnboardingWelcomeEvent) {}
}
