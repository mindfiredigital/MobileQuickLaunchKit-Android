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
    namespace = "com.foss.settings"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    //Need below three in every module
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    compose()
    composeNavigation()
    core()
    coreUI()
    shared()
    coilImage()
    retrofit()
    firebase()
    timber()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "settings"
            version = PublishingConfig.settingsVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

