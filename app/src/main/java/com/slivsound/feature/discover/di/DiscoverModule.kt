package com.slivsound.feature.discover.di

import com.slivsound.feature.discover.buildKtorClient
import com.slivsound.feature.discover.data.mapper.MelodiesService
import com.slivsound.feature.discover.data.repository.DiscoverRepositoryImpl
import com.slivsound.feature.discover.domain.repository.DiscoverRepository
import com.slivsound.feature.discover.presentation.DiscoverViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val discoverKtorModule = module {
    single(named("baseUrl")) { "https://cdn.jsdelivr.net/gh/slivsound-tech/slivsound-content@1.0.1/" }
    single { buildKtorClient() }
    single { MelodiesService(client = get(), baseUrl = get(named("baseUrl"))) }
    single<DiscoverRepository> {
        DiscoverRepositoryImpl(
            service = get(),
            baseUrl = get(named("baseUrl"))
        )
    }
    viewModel { DiscoverViewModel(repository = get()) }
}