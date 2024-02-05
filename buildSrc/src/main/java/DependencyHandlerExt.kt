import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 *
 * @Description: This file contain extension handler for dependency handler
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 11-12-2023
 */

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(dependency: Dependency) {
    add("implementation", dependency)
}

fun DependencyHandler.test(dependency: String) {
    add("test", dependency)
}

fun DependencyHandler.androidTest(dependency: String) {
    add("androidTest", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: Dependency) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.kaptAndroidTest(dependency: String) {
    add("kaptAndroidTest", dependency)
}

fun DependencyHandler.kaptTest(dependency: String) {
    add("kaptTest", dependency)
}