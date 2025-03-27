package com.angelme.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*,*,*,*,*,*>,
    extensionType: ExtensionType
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }
        val apiKey = gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY")
        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugType(apiKey)
                        }
                        release {
                            configureReleaseType(apiKey, commonExtension)
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugType(apiKey)
                        }
                        release {
                            configureReleaseType(apiKey, commonExtension)
                        }
                    }
                }
            }
            ExtensionType.DYNAMIC_FEATURE -> {
                extensions.configure<DynamicFeatureExtension> {
                    buildTypes {
                        debug {
                            configureDebugType(apiKey)
                        }
                        release {
                            configureReleaseType(apiKey, commonExtension)
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugType(apiKey: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun BuildType.configureReleaseType(
    apiKey: String,
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}