plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("maven-publish")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.foss.shared"

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
    compose()
    hilt()
    timber()
    dataStore()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.${PublishingConfig.githubUser}"
            artifactId = "shared"
            version = PublishingConfig.sharedVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}