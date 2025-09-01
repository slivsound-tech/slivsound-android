package com.slivsound.featurename.data

import com.slivsound.featurename.domain.NameRepository
import org.koin.core.annotation.Single

@Single(binds = [NameRepository::class])
class NameRepositoryImpl : NameRepository {
    override fun getName(): String {
        return "Test example Koin!"
    }
}