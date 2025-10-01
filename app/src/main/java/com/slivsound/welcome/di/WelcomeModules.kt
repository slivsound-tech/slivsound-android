package com.slivsound.welcome.di

import com.slivsound.welcome.data.WelcomeRepositoryImpl
import com.slivsound.welcome.domain.WelcomeRepository
import org.koin.android.annotation.KoinViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val welcomeModule = module {

    // Репозиторий
    single<WelcomeRepository> { WelcomeRepositoryImpl() }

    // ViewModel
    //singleOf(::WelcomeViewModel)
}
