import com.android.build.gradle.LibraryExtension
import consts.Constants
import ext.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Library module 용 convention plugin
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
                apply("kotlin-kapt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    targetSdk = Constants.targetSdk
                    buildConfigField("String", "versionName", "\"${Constants.versionName}\"")
                }
                buildFeatures {
                    viewBinding = true
                    dataBinding = true
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findBundle("kotlin").get())
                "implementation"(libs.findBundle("androidx").get())
                "implementation"(libs.findBundle("unit.test").get())
                "implementation"(libs.findBundle("third-parties").get())
                "androidTestImplementation"(libs.findBundle("android.test").get())
            }
        }
    }
}