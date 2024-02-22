//Need below two plugins in every module
plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("maven-publish")
}
apply<MainGradlePlugin>()


android {
    namespace = "com.foss.home"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    //Need below three in every module
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    compose()
    composeNavigation()
    core()
    coreUI()
    authData()
    authDomain()

}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "home"
            version = PublishingConfig.homeVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}