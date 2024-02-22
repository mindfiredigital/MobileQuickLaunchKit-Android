plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("maven-publish")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.foss.core"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    //Need below three in every module
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    retrofit()
    timber()
    dataStore()
    moshi()

}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "core"
            version = PublishingConfig.coreVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}