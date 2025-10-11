package com.slivsound.feature.discover.domain

interface DiscoverRepository {
    suspend fun fetchMelodies(): Result<List<SoundModel>>
    suspend fun fetchSounds(): Result<List<SoundModel>>
    suspend fun getNoiseforSleep(): Result<List<SoundModel>>
}