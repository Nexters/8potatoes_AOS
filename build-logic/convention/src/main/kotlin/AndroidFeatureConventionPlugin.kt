import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * feature module 들을 위한 convention plugin
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("hyusik.android.library")
                apply("hyusik.android.hilt")
                apply("hyusik.android.library.compose")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:domain"))

                "implementation"(libs.findBundle("material.design").get())
                "implementation"(libs.findBundle("image").get())
            }
        }
    }
}