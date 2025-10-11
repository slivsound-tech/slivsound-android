package com.slivsound.feature.discover.data

import com.slivsound.feature.discover.data.model.toDomain
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel

class DiscoverRepositoryImpl(
    private val service: DiscoverApiRepository,
) : DiscoverRepository {

    override suspend fun fetchMelodies(): Result<List<SoundModel>> = runCatching {
         service.getMelodies().toDomain()
    }
    override suspend fun fetchSounds(): Result<List<SoundModel>> = runCatching {
        service.getSounds().toDomain()
    }
    override suspend fun getNoiseforSleep(): Result<List<SoundModel>> = runCatching {
        service.getNoiseforSleep().toDomain()
    }
}