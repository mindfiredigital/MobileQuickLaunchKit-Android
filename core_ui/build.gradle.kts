plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.foss.core_ui"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }


    defaultConfig {
        resourceConfigurations.plus(listOf("ta", "en", "hi", "your-language-tag"))

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
}