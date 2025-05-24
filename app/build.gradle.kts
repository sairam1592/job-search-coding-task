import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        namespace = "seek.codingtask"

        versionCode = 1
        versionName = "0.0.1"

        minSdk = 26
        compileSdk = 34
        targetSdk = 34

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "11"
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        )
    }

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(libs.seek.core.presentation)
    implementation(libs.material3)

    implementation(libs.seek.core.domain)

    implementation(libs.seek.core.data)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)

    implementation(libs.timber)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    testImplementation(libs.seek.core.presentation.test)
    testImplementation(libs.seek.core.domain.test)
    testImplementation(libs.seek.core.data.test)

    debugImplementation(libs.ui.test.manifest)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.seek.core.instrumented.test) {
        exclude(group = "com.google.guava")
        exclude(group = "javax.inject")
    }

    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)

    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
}
