package ext

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(
    libs: VersionCatalog,
    bundleName: String
) {
    "implementation"(libs.findBundle(bundleName).get())
}

internal fun DependencyHandlerScope.androidTestImplementation(
    libs: VersionCatalog,
    bundleName: String
) {
    "androidTestImplementation"(libs.findBundle(bundleName).get())
}

