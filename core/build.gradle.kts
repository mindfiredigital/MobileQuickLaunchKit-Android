plugins {
    `android-library`
    `kotlin-android`
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