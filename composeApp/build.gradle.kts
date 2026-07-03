import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }


    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.viewmodel)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Ktor Client

            // Room 3.0

            // Navigation3 (navigation3-runtime is bundled in navigation3-ui)
            implementation(libs.navigation3.ui)

            // Kotlin Serialization
            implementation(libs.kotlinx.serialization.json)

            // Coil 3
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            // kotlinx-datetime
            implementation(libs.kotlinx.datetime)

            implementation(libs.peekaboo.image.picker)

            // multiplatform-settings
            implementation(libs.firebase.common)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.storage)
            implementation(project.dependencies.platform(libs.firebase.bom))



        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
            implementation(libs.kotest.assertions)
            implementation(libs.mockative)
        }
    }

    android {
        namespace = "io.jadu.phoenix"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        androidResources {
            enable = true
        }
    }
}


dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

    // Room KSP processors

    // Mockative KSP processor
    add("kspCommonMainMetadata", libs.mockative.processor)
    add("kspAndroid", libs.mockative.processor)
    add("kspIosArm64", libs.mockative.processor)
    add("kspIosSimulatorArm64", libs.mockative.processor)
}

// Room schema directory configured via KSP arguments
