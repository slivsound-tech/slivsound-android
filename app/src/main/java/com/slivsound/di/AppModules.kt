package com.slivsound.onboarding.di

import org.koin.ksp.generated.module
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.slivsound")
class AppModules

fun appModules() = listOf(AppModules().module)