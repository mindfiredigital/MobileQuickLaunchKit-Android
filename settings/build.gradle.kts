//Need below two plugins in every module
plugins {
    `android-library`
    `kotlin-android`
}
apply<MainGradlePlugin>()


android {
    namespace = "com.foss.settings"
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