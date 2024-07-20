import com.android.build.api.dsl.ApplicationExtension
import ext.androidTestImplementation
import ext.configureDefault
import ext.configureKotlinAndroid
import ext.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefault()
                defaultConfig.targetSdk = 34

                buildFeatures {
                    viewBinding = true
                    dataBinding = true
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                implementation(libs, "kotlin")
                implementation(libs, "androidx")
                implementation(libs, "unit.test")
                implementation(libs, "material.design")
                androidTestImplementation(libs, "android.test")
            }
        }
    }
}