package com.slivsound.feature.discover.data

import com.slivsound.feature.discover.data.model.toDomain
import com.slivsound.feature.discover.domain.DiscoverRepository
import com.slivsound.feature.discover.domain.SoundModel

class DiscoverRepositoryImpl(
    private val service: DiscoverApiRepository,
) : DiscoverRepository {
    private val cache = mutableMapOf<String, SoundModel>()

    override suspend fun fetchMelodies(): Result<List<SoundModel>> = runCatching {
        service.getMelodies()
            .toDomain()
            .also { list -> list.forEach { cache[it.id] = it } }
    }

    override suspend fun fetchSounds(): Result<List<SoundModel>> = runCatching {
        service.getSounds()
            .toDomain()
            .also { list -> list.forEach { cache[it.id] = it } }
    }

    override suspend fun getNoiseforSleep(): Result<List<SoundModel>> = runCatching {
        service.getNoiseforSleep()
            .toDomain()
            .also { list -> list.forEach { cache[it.id] = it } }
    }
    override fun getSoundById(id: String): SoundModel? = cache[id]
}