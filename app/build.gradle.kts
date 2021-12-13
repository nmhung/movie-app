plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID) // or kotlin("android") or id 'kotlin-android'
    id(GradlePluginId.KOTLIN_KAPT) // or kotlin("kapt")
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.HILT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Libs.CORE_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.COROUTINES)
    implementation(Libs.SWIPE_REFRESH_LAYOUT)

    implementation(Libs.NAVIGATION_KTX)

    // Hilt
    implementation(Libs.HILT)
    kapt(Libs.HILT_ANDROID_COMPILER)
    kapt(Libs.HILT_COMPILER)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.OKHTTP3_LOGGING_INTERCEPTOR)

    // Moshi
    implementation(Libs.RETROFIT_CONVERTER_MOSHI)
    implementation(Libs.MOSHI)
    implementation(Libs.MOSHI_KOTLIN)
    kapt(Libs.MOSHI_KOTLIN_CODEGEN)

    // ViewModel
    implementation(Libs.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Libs.ACTIVITY_KTX)

    // LiveData
    implementation(Libs.LIFECYCLE_LIVEDATA_KTX)
    implementation(Libs.LIFECYCLE_KTX)
    implementation(Libs.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    kapt(Libs.LIFECYCLE_COMPILER)

    implementation(Libs.TIMBER)

    // Navigation
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)

    // Epoxy
    implementation(Libs.EPOXY)
    implementation(Libs.EPOXY_DATABINDING)
    kapt(Libs.EPOXY_PROCESSOR)

    // Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)


    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)
}