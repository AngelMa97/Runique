plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.angelme.wear.run.data"

    defaultConfig {
        minSdk = 30
    }
}

dependencies {
    implementation(libs.androidx.health.services.client)

    implementation(libs.bundles.koin)

    implementation(projects.wear.run.domain)
    implementation(projects.core.domain)
    implementation(projects.core.connectivity.domain)
}