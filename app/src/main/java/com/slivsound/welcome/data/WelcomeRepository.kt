package com.slivsound.welcome.data

import com.slivsound.welcome.domain.WelcomeRepository
import org.koin.core.annotation.Single

@Single(binds = [WelcomeRepository::class])
class WelcomeRepositoryImpl : WelcomeRepository {
    override fun getWelcomeMessage(): String {
        return "Welcome to SlivSound!"
    }
}
