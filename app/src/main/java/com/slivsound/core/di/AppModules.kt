package com.slivsound.core.di

import com.slivsound.core.network.ApiClient
import com.slivsound.feature.discover.di.discoverModule
import com.slivsound.feature.sound.di.playModule
import com.slivsound.feature.sound.di.soundModule
import org.koin.dsl.module


val appModule = module {
    single { ApiClient.client }
}

fun appModules() = listOf(
    appModule,
    playModule,
    discoverModule,
    soundModule,
)

