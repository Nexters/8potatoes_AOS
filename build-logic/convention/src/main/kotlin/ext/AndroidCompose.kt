package ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureComposeAndroid(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    commonExtension.apply {
        buildFeatures { compose = true }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.3.1"
        }
    }
    dependencies {
        "implementation"(platform(libs.findDependency("compose-bom").get()))
        "implementation"(libs.findBundle("compose").get())
    }
}