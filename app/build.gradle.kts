plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
}

android {
    namespace = "com.slivsound"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.slivsound"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true

    }
}

// KSP config
ksp {
    arg("KOIN_DEFAULT_MODULE", "true")
}

dependencies {


    implementation("io.ktor:ktor-client-resources:2.3.8")
    implementation("io.insert-koin:koin-android:3.5.6")
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")

    // koin core + android
    implementation("io.insert-koin:koin-android:3.5.6")


    // для Compose
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")

    implementation("io.coil-kt.coil3:coil-compose:<latest>")
    implementation("io.coil-kt.coil3:coil-network-okhttp:<latest>")

    implementation("io.ktor:ktor-client-core:2.3.12")
    implementation("io.ktor:ktor-client-okhttp:2.3.12")              // движок под Android
    implementation("io.ktor:ktor-client-content-negotiation:2.3.12") // JSON
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12") // kotlinx.serialization
    implementation("io.ktor:ktor-client-logging:2.3.12")             // лог HTTP
    implementation("io.ktor:ktor-client-resources:2.3.12")           // (опционально) типобезопасные пути

    // JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Coroutines + lifecycle
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    // Koin Annotations (KSP)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}