pluginManagement {
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
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ActionPower"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":feature")
include(":feature:search")
include(":core:network")
include(":core:database")
include(":core:model")
include(":feature:detail")
include(":core:designsystem")
include(":feature:youtubeplayer")
include(":feature:video")
include(":core:util")
