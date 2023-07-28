package com.zyn.plugin

object Versions {
    const val koin_version = "3.1.5"
}

/** Android X */
object AndroidX {
    const val testJunit = "androidx.test.ext:junit:1.1.1"
    const val espresso = "androidx.test.espresso:espresso-core:3.2.0"

    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val design = "com.google.android.material:material:1.5.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val junit = "junit:junit:4.12"
    const val runner = "androidx.test.ext:junit:1.1.1"
    const val espresso_core = "androidx.test.espresso:espresso-core:3.1.0"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:2.0.0"
    const val multidex = "androidx.multidex:multidex:2.0.0"
    const val kotlin_lib = "org.jetbrains.kotlin:kotlin-stdlib:1.3.72"
    const val kotlin_core = "androidx.core:core-ktx:1.7.0"
    const val guava = "com.google.guava:guava:27.0.1-android"


    //协程
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
}


//阿里路由
object Alibaba {
    const val arouter = "com.alibaba:arouter-api:1.5.2"
    const val arouterCompiler = "com.alibaba:arouter-compiler:1.5.2"
    const val fastjson = "com.alibaba:fastjson:1.2.73"
}

/** glide 图片加载 */
object Glide {
    const val glide = "com.github.bumptech.glide:glide:4.11.0"
    const val glide_compiler = "com.github.bumptech.glide:compiler:4.11.0"
    const val glide_okhttp3_integration = "com.github.bumptech.glide:okhttp3-integration:4.3.1@aar"
    const val glide_transfer = "jp.wasabeef:glide-transformations:4.3.0"
}

/** 第三方引入 */
object Dependencies {
    const val blankjUtils = "com.blankj:utilcodex:1.31.1"
    //rxbinding
    const val rxbinding = "com.jakewharton.rxbinding3:rxbinding:3.0.0"

    //rx系列与View生命周期同步
    const val rxlifecycle = "com.trello.rxlifecycle3:rxlifecycle:3.0.0"
    const val rxlifecycle_components = "com.trello.rxlifecycle3:rxlifecycle-components:3.0.0"
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    const val toast = "com.github.GrenderG:Toasty:1.5.0"

    const val xpopup = "com.lxj:xpopup:2.2.5"

    /** 用于实现一个activity 多个Fragment 的架构 */
    const val fragmentationx = "me.yokeyword:fragmentationx:1.0.2"

    const val koin_core = "io.insert-koin:koin-android:${Versions.koin_version}"

    const val libImmersionbar = "com.gyf.immersionbar:immersionbar:3.0.0"

    //network
    const val okhttp = "com.squareup.okhttp3:okhttp:3.10.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:2.9.0"

    //rxjava
    val adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava2:2.9.0"

    val utils = "com.blankj:utilcodex:1.31.1"

    val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

    //国密加密组件
    val crypto = "com.tencent.kona:kona-crypto:1.0.4"
}