plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.google.ksp)
    id("kotlin-kapt")
}

android {
    namespace = "com.muhtarkhan.ncourseproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.muhtarkhan.ncourseproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.adaptive.android)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.fragment)
    implementation(libs.activity)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    implementation(libs.okhttp)
    implementation(libs.kotlin.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.retrofit.kotlin.result)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.coil)
    implementation(libs.coil.network)
    implementation(libs.koin)
    implementation(libs.workManager)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.media3.common.ktx)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckerNoOp)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutine.test)
    testImplementation(kotlin("test"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)




    ksp(libs.androidx.room.compiler)
}