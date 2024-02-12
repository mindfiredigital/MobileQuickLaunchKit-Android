plugins {
    `android-library`
    `kotlin-android`
}
apply<MainGradlePlugin>()

android {
    namespace = "com.foss.shared"
    kotlinOptions {
        jvmTarget = "18"
    }
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
