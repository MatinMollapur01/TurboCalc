plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0") // Adjust version as necessary
        classpath("com.android.tools.lint:lint-gradle:30.4.2") // Adjust to a known available version
    }
}
