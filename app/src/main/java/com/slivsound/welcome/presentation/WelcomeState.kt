package com.slivsound.welcome.presentation

data class WelcomeState(
    val message: String = "Welcome to your space of sounds",
    val message2: String = "Relax, mix your favorite sounds, and drift into deep sleep.",
    val isLoading: Boolean = false
)
