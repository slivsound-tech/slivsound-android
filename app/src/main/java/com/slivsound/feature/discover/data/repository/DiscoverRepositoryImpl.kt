package com.slivsound.feature.discover.data.repository

import com.slivsound.feature.discover.data.mapper.MelodiesService
import com.slivsound.feature.discover.domain.model.Melody
import com.slivsound.feature.discover.domain.repository.DiscoverRepository
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.core.annotation.Single


@Single(binds = [DiscoverRepository::class])
class DiscoverRepositoryImpl(
    private val service: MelodiesService,
    private val baseUrl: String
) : DiscoverRepository {

    override suspend fun fetchMelodies(): Result<List<Melody>> = runCatching {
        val dto = service.getMelodies()
        val base: HttpUrl = baseUrl.toHttpUrl()
        dto.tracks.map { t ->
            Melody(
                id = t.id,
                title = t.name,
                description = t.description,
                imageUrl = base.newBuilder().addPathSegments(t.imagePath.trimStart('/')).build()
                    .toString(),
                audioUrl = base.newBuilder().addPathSegments(t.audioPath.trimStart('/')).build()
                    .toString(),
                tags = t.tags
            )
        }
    }

}