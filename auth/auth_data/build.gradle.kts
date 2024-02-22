plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("maven-publish")
}

apply<MainGradlePlugin>()


android {
    namespace = "com.foss.auth_data"
}

dependencies {
    //Need below three in every module
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    retrofit()
    core()
    authDomain()
    biometrics()
    shared()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "auth_data"
            version = PublishingConfig.authVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}