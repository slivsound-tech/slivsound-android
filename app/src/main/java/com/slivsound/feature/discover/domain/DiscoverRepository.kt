package com.slivsound.feature.discover.domain

interface DiscoverRepository {
    suspend fun fetchMelodies(): Result<List<SoundModel>>
    suspend fun fetchSounds(): Result<List<SoundModel>>
    suspend fun getNoiseforSleep(): Result<List<SoundModel>>
    fun getSoundById(id: String): SoundModel?

    fun saveLastSound(sound: SoundModel)
    fun getLastSound(): SoundModel?
}