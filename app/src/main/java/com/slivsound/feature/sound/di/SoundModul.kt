package com.slivsound.feature.sound.di

import com.slivsound.feature.discover.data.DiscoverApiRepository
import com.slivsound.feature.discover.data.DiscoverApiRepositoryImpl
import com.slivsound.feature.discover.data.DiscoverRepositoryImpl
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.sound.presentation.SoundViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val soundModule = module {
    single< DiscoverApiRepository> { DiscoverApiRepositoryImpl(get()) }
    single<DiscoverRepository> { DiscoverRepositoryImpl(get()) }
    viewModelOf(::SoundViewModel)


}