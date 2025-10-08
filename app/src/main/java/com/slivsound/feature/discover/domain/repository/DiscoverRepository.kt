package com.slivsound.feature.discover.domain.repository

import com.slivsound.feature.discover.domain.model.Melody

interface DiscoverRepository {
    suspend fun fetchMelodies(): Result<List<Melody>>

}