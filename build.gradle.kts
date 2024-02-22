buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.hiltAgp)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.google.gms:google-services:4.4.1")
    }
}

subprojects {
    tasks.matching { task ->
        task.name.contains("javaDocReleaseGeneration", ignoreCase = true)
    }.configureEach {
        enabled = false
    }

}

