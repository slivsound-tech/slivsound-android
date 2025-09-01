package com.slivsound.app.featurename.data

import com.slivsound.app.featurename.domain.NameRepository
import org.koin.core.annotation.Single

@Single(binds = [NameRepository::class])
class NameRepositoryImpl : NameRepository {
    override fun getName(): String {
        return "Test example Koin!"
    }
}