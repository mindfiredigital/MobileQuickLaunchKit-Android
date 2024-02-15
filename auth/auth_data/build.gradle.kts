plugins {
    `android-library`
    `kotlin-android`
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