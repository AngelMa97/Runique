plugins {
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "com.angelme.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}