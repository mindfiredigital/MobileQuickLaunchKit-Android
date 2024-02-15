import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 11-12-2023
 */

object Dependencies {

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val androidLifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"

    const val activityCompose =
        "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"

    // ViewModel utilities for Compose
    const val lifecycleViewmodelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}"

    // Lifecycle utilities for Compose
    const val lifecycleRuntimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleVersion}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiKotlinVersion}"
    const val moshiKotlinCodegen =
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiKotlinVersion}"
    const val moshiLazyAdapter =
        "com.serjltt.moshi:moshi-lazy-adapters:${Versions.moshiLazyAdapters}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"

    const val coilImage = "io.coil-kt:coil-compose:${Versions.coilImageVersion}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    const val navigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}"
    const val navigationRuntime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.navigationRuntime}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    // For hilt instrumentation/unit tests
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"

    const val junit4 = "junit:junit:${Versions.junitVersion}"
    const val junitAndroidExt = "androidx.test.ext:junit:${Versions.junitAndroidExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    const val firebaseAuth = "com.google.firebase:firebase-auth:22.3.0"
    const val playServicesAuth = "com.google.android.gms:play-services-auth:20.7.0"
    const val biometrics = "androidx.biometric:biometric:1.2.0-alpha05"
    const val dataStore = "androidx.datastore:datastore-preferences:1.0.0-alpha01"
}


fun DependencyHandler.firebase() {
    implementation(Dependencies.firebaseAuth)
    implementation(Dependencies.playServicesAuth)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.dataStore)
}

fun DependencyHandler.biometrics() {
    implementation(Dependencies.biometrics)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.lifecycleViewmodelCompose)
    implementation(Dependencies.lifecycleRuntimeCompose)
    androidTestImplementation(platform(Dependencies.composeBom))
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
}

fun DependencyHandler.unitTesting() {
    testImplementation(Dependencies.junit4)
    androidTestImplementation(Dependencies.junitAndroidExt)
    androidTestImplementation(Dependencies.espresso)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.hiltTesting() {
    androidTestImplementation(Dependencies.hiltAndroidTesting)
    kaptAndroidTest(Dependencies.hiltCompiler)
    testImplementation(Dependencies.hiltAndroidTesting)
    kaptTest(Dependencies.hiltCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiKotlinCodegen)
    implementation(Dependencies.moshiLazyAdapter)
}

fun DependencyHandler.constraintLayout() {
    implementation(Dependencies.constraintLayout)
}

fun DependencyHandler.coilImage() {
    implementation(Dependencies.coilImage)
}
fun DependencyHandler.moshi() {
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiKotlinCodegen)
}

fun DependencyHandler.timber() {
    implementation(Dependencies.timber)
}

fun DependencyHandler.composeNavigation() {
    implementation(Dependencies.navigationRuntime)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.hiltNavigationCompose)
}

//Inroduce a single module
fun DependencyHandler.onboardingPresentation() {
    implementation(project(":onboarding:onboarding_presentation"))
}

fun DependencyHandler.coreUI() {
    implementation(project(":core_ui"))
}

fun DependencyHandler.core() {
    implementation(project(":core"))
}

fun DependencyHandler.authDomain() {
    implementation(project(":auth:auth_domain"))
}

fun DependencyHandler.authPresentation() {
    implementation(project(":auth:auth_presentation"))
}

fun DependencyHandler.authData() {
    implementation(project(":auth:auth_data"))
}

fun DependencyHandler.home() {
    implementation(project(":home"))
}

fun DependencyHandler.settings() {
    implementation(project(":settings"))
}

fun DependencyHandler.sample() {
    implementation(project(":sample"))
}

fun DependencyHandler.utilities() {
    implementation(project(":utility"))
}

fun DependencyHandler.shared() {
    implementation(project(":shared"))
}
