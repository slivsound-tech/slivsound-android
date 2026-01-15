package com.slivsound.feature.discover.di

import com.slivsound.feature.discover.data.DiscoverApiRepository
import com.slivsound.feature.discover.data.DiscoverApiRepositoryImpl
import com.slivsound.feature.discover.data.DiscoverRepositoryImpl
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.presentation.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val discoverModule = module {
    single< DiscoverApiRepository> { DiscoverApiRepositoryImpl(get()) }
    single<DiscoverRepository> { DiscoverRepositoryImpl(get()) }
    viewModel { DiscoverViewModel(playrepository = get(), repository = get())  }
}