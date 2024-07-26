pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://repository.map.naver.com/archive/maven") }
    }
}

rootProject.name = "HyusikMatju"
include(":app")

// feature
include(":feature:search")

// core
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:designsystem")
include(":core:common")
