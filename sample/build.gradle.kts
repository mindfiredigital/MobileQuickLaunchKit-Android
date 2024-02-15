//Need below two plugins in every module
plugins {
    `android-library`
    `kotlin-android`
}
apply<MainGradlePlugin>()


android {
    namespace = "com.foss.sample"
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
    implementation("com.google.firebase:firebase-auth:22.3.0")
    //Default dependencies
    Dependencies.coreKtx
    Dependencies.androidLifecycleRuntime
    compose()
    unitTesting()
    hilt()
    hiltTesting()
    retrofit()
    constraintLayout()
    coilImage()
    timber()
    composeNavigation()

    onboardingPresentation()
    coreUI()
    core()
    authData()
    authDomain()
    authPresentation()
    home()
    settings()
    utilities()

}