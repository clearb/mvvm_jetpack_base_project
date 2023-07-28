import com.zyn.plugin.AndroidX
import com.zyn.plugin.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.zyn.http"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(AndroidX.appcompat)
    api(AndroidX.junit)
    api(AndroidX.design)
    api(AndroidX.espresso_core)
    api(AndroidX.runner)
    //network
    api(Dependencies.okhttp)
    api(Dependencies.retrofit)
    api(Dependencies.converter_gson)
    api(Dependencies.adapter_rxjava)
    //第三方
    api(Dependencies.utils)
    //rxandroid
    api(Dependencies.rxandroid)
    //国密
    api(Dependencies.crypto)
    api(AndroidX.coroutines_android)
    api(AndroidX.coroutines_core)
}