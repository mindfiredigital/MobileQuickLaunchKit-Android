plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("maven-publish")
}

apply<MainGradlePlugin>()


android {
    namespace = "com.foss.auth_domain"
}


dependencies {
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    retrofit()
    core()
    biometrics()
    shared()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "auth_domain"
            version = PublishingConfig.authVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}