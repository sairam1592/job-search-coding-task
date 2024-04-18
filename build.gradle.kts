plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlinter) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("file://$rootDir/libs/repository")
        }
    }

    apply {
        plugin("org.jmailen.kotlinter")
    }
}
