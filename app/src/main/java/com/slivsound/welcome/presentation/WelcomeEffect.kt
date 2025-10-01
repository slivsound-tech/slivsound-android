package com.slivsound.welcome.presentation

sealed interface WelcomeEffect {
    data class ShowToast(val message: String) : WelcomeEffect
    data class ShowError(val message: String) : WelcomeEffect
}
