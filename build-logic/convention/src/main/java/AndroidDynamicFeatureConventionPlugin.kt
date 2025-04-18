import com.android.build.api.dsl.DynamicFeatureExtension
import com.angelme.convention.ExtensionType
import com.angelme.convention.addUiLayerDependencies
import com.angelme.convention.configureAndroidCompose
import com.angelme.convention.configureBuildTypes
import com.angelme.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidDynamicFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.dynamic-feature")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<DynamicFeatureExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.DYNAMIC_FEATURE
                )

                dependencies {
                    addUiLayerDependencies(target)
                    "testImplementation"(kotlin("test"))
                }
            }
        }
    }
}