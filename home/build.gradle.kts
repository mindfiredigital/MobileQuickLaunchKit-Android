//Need below two plugins in every module
plugins {
    `android-library`
    `kotlin-android`
}
apply<MainGradlePlugin>()


android {
    namespace = "com.foss.home"
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