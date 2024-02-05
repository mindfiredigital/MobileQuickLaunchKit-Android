plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.foss.auth_presentation"
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
}