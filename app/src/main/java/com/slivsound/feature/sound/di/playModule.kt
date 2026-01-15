package com.slivsound.feature.sound.di

import com.slivsound.feature.sound.presentation.repositiry.PlayRepository
import com.slivsound.feature.sound.presentation.repositiry.PlayRepositoryImpl
import org.koin.dsl.module

val playModule = module {
    single< PlayRepository> { PlayRepositoryImpl() }
}