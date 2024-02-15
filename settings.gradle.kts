pluginManagement {
    repositories {
        google()
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

rootProject.name = "MobileQuickLaunchKit"
include(":app")
include(":core")
include(":core_ui")
include(":onboarding:onboarding_presentation")
include(":auth:auth_data")
include(":auth:auth_domain")
include(":auth:auth_presentation")
include(":home")
include(":settings")
include(":sample")
include(":utility")
include(":shared")
