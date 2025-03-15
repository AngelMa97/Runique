plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.angelme.run.location"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}