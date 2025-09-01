package com.slivsound.app.di

import org.koin.ksp.generated.module
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.slivsound.app")
class AppModules

// Expose a helper to collect modules list in Application
fun appModules() = listOf(AppModules().module)