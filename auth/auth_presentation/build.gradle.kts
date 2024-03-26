plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("maven-publish")

}

apply<MainGradlePlugin>()

android {
    namespace = "com.foss.auth_presentation"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    retrofit()
    compose()
    composeNavigation()
    core()
    coreUI()
    authDomain()
    firebase()
    biometrics()
    timber()
    shared()
    utilities()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "auth_presentation"
            version = PublishingConfig.authVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}