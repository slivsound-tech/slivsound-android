package com.slivsound.feature.discover.data

import com.slivsound.feature.discover.data.model.MelodiesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface DiscoverApiRepository {
    suspend fun getMelodies(): MelodiesDto
    suspend fun getSounds(): MelodiesDto
    suspend fun getNoiseforSleep(): MelodiesDto

}

class DiscoverApiRepositoryImpl(
    private val api: HttpClient,
) : DiscoverApiRepository {
    override suspend fun getMelodies(): MelodiesDto {
        val data = api.get("discover/v1/melodies/catalog.json")
        return data.body<MelodiesDto>()
    }

    override suspend fun getSounds(): MelodiesDto {
        val data = api.get("discover/v1/sounds/catalog.json")
        return data.body<MelodiesDto>()
    }

    override suspend fun getNoiseforSleep(): MelodiesDto {
        val data = api.get("discover/v1/noises/catalog.json")
        return data.body<MelodiesDto>()
    }
}