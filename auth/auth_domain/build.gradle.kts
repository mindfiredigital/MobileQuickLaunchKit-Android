plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()


android {
    namespace = "com.foss.auth_domain"
}


dependencies {
    Dependencies.coreKtx
    Dependencies.appCompat
    hilt()
    hiltTesting()
    retrofit()
    core()
    biometrics()
}