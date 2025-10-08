package com.slivsound.feature.discover.data.mapper

import com.slivsound.feature.discover.data.remote.dto.MelodiesDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl


class MelodiesService(
    private val client: io.ktor.client.HttpClient,
    private val baseUrl: String
) {
    private val base: HttpUrl = baseUrl.toHttpUrl()

    private fun resolve(path: String): String =
        base.newBuilder()
            .addPathSegments(path.trimStart('/'))
            .build()
            .toString()

    suspend fun getMelodies(): MelodiesDto =
        client.get(resolve("discover/v1/melodies/catalog.json")).body<MelodiesDto>()


}