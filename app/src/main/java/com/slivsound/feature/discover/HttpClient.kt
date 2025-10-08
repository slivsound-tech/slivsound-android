package com.slivsound.feature.discover


import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


fun buildKtorClient() = io.ktor.client.HttpClient(
    OkHttp
) {
    expectSuccess = false
    install(Resources)
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true; isLenient = true; explicitNulls = false
        })
    }
    install(io.ktor.client.plugins.logging.Logging) {
        logger = io.ktor.client.plugins.logging.Logger.SIMPLE
        level = io.ktor.client.plugins.logging.LogLevel.INFO
    }
    install(io.ktor.client.plugins.HttpTimeout) {
        requestTimeoutMillis = 15_000; connectTimeoutMillis = 10_000; socketTimeoutMillis = 15_000
    }
}
