import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @Description: Custom Gradle Plugin To Share In WHole CodeBase
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 12-12-2023
 */


class MainGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
        }
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
            compileSdk = ProjectConfig.compileSdk

            defaultConfig {
                minSdk = ProjectConfig.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            publishing {
                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                        withJavadocJar()
                    }
                }
            }

        }
    }

    private fun Project.android(): LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}