package com.slivsound.welcome.presentation

sealed interface WelcomeEvent {
    data object SetupWelcome : WelcomeEvent
    data class SetWelcome(val message: String) : WelcomeEvent
}
