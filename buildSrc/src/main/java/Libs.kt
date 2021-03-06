object LibsVersions {
    const val APPCOMPAT = "1.4.0"
    const val MATERIAL = "1.4.0"
    const val CORE_KTX = "1.7.0"
    const val CONSTRAINT_LAYOUT = "2.1.2"
    const val COROUTINES = "1.5.1"
    const val SWIPE_REFRESH_LAYOUT = "1.1.0"
    const val HILT = "2.40.5"
    const val HILT_COMPILER = "1.0.0"
    const val RETROFIT = "2.9.0"
    const val OKHTTP3_LOGGING_INTERCEPTOR = "4.9.1"
    const val RETROFIT_CONVERTER_MOSHI = "2.9.0"
    const val MOSHI = "1.13.0"
    const val LIFECYCLE = "2.3.1"
    const val ACTIVITY_KTX = "1.3.1"
    const val TIMBER = "5.0.0"
    const val NAVIGATION = "2.3.5"
    const val EPOXY = "4.6.3-vinay-compose"
    const val GLIDE = "4.12.0"
    const val MAPS = "18.0.1"
    const val MAPS_KTX = "3.2.1"
    const val MAPS_LOCATION = "19.0.0"
    const val LEAKCANARY = "2.7"

    // Test
    const val JUNIT = "4.+"
    const val EXT_JUNIT = "1.1.3"
    const val ESPRESSO_CORE = "3.4.0"
    const val MOCKK = "1.12.1"
    const val ARCH = "2.1.0"
    const val KLUENT = "1.68"
    const val JUPITER = "5.8.2"
}


object Libs {
    const val CORE_KTX = "androidx.core:core-ktx:${LibsVersions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${LibsVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${LibsVersions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibsVersions.CONSTRAINT_LAYOUT}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibsVersions.COROUTINES}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibsVersions.SWIPE_REFRESH_LAYOUT}"
    const val HILT = "com.google.dagger:hilt-android:${LibsVersions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${LibsVersions.HILT}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${LibsVersions.HILT_COMPILER}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibsVersions.RETROFIT}"
    const val OKHTTP3_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibsVersions.OKHTTP3_LOGGING_INTERCEPTOR}"
    const val RETROFIT_CONVERTER_MOSHI =
        "com.squareup.retrofit2:converter-moshi:${LibsVersions.RETROFIT_CONVERTER_MOSHI}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${LibsVersions.MOSHI}"
    const val MOSHI = "com.squareup.moshi:moshi:${LibsVersions.MOSHI}"
    const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${LibsVersions.MOSHI}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibsVersions.LIFECYCLE}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${LibsVersions.ACTIVITY_KTX}"
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${LibsVersions.LIFECYCLE}"
    const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${LibsVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_SAVEDSTATE =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${LibsVersions.LIFECYCLE}"
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${LibsVersions.LIFECYCLE}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibsVersions.TIMBER}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${LibsVersions.NAVIGATION}"
    const val NAVIGATION_KTX =
        "androidx.navigation:navigation-runtime-ktx:${LibsVersions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${LibsVersions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURE_FRAGMENT =
        "androidx.navigation:navigation-dynamic-features-fragment:${LibsVersions.NAVIGATION}"
    const val EPOXY = "com.airbnb.android:epoxy:${LibsVersions.EPOXY}"
    const val EPOXY_PROCESSOR = "com.airbnb.android:epoxy-processor:${LibsVersions.EPOXY}"
    const val EPOXY_DATABINDING = "com.airbnb.android:epoxy-databinding:${LibsVersions.EPOXY}"
    const val GLIDE = "com.github.bumptech.glide:glide:${LibsVersions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${LibsVersions.GLIDE}"
    const val MAPS = "com.google.android.gms:play-services-maps:${LibsVersions.MAPS}"
    const val MAPS_KTX = "com.google.maps.android:maps-ktx:${LibsVersions.MAPS_KTX}"
    const val MAPS_UTILS_KTX = "com.google.maps.android:maps-utils-ktx:${LibsVersions.MAPS_KTX}"
    const val MAPS_LOCATION =
        "com.google.android.gms:play-services-location:${LibsVersions.MAPS_LOCATION}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${LibsVersions.COROUTINES}"
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:${LibsVersions.LEAKCANARY}"


    // Test
    const val JUNIT = "junit:junit:${LibsVersions.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${LibsVersions.EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${LibsVersions.ESPRESSO_CORE}"

    const val MOCKK = "io.mockk:mockk:${LibsVersions.MOCKK}"
    const val ARCH = "androidx.arch.core:core-testing:${LibsVersions.ARCH}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${LibsVersions.COROUTINES}"
    const val KLUENT = "org.amshove.kluent:kluent-android:${LibsVersions.KLUENT}"
    const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${LibsVersions.JUPITER}"
    const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${LibsVersions.JUPITER}"
}