package com.slivsound.welcome.domain

interface WelcomeRepository {
    fun getWelcomeMessage(): String
}
