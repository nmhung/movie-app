plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID) // or kotlin("android") or id 'kotlin-android'
    id(GradlePluginId.KOTLIN_KAPT) // or kotlin("kapt")
    id(GradlePluginId.ANDROID_JUNIT_5)
    id(GradlePluginId.HILT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK_VERSION

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    flavorDimensions("server")
    productFlavors {
        create("dev") {
            buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_TOKEN", "\"4479d7f3c064d397119eb3286deba0e1\"")
        }
        create("prod") {
            buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_TOKEN", "\"4479d7f3c064d397119eb3286deba0e1\"")
        }
    }

    // Removes the need to mock need to mock classes that may be irrelevant from test perspective
    testOptions {
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }
}


hilt {
    enableExperimentalClasspathAggregation = true
}

kapt {
    correctErrorTypes = true
}

dependencies {

    // Hilt
    api(Libs.HILT)
    kapt(Libs.HILT_ANDROID_COMPILER)
    kapt(Libs.HILT_COMPILER)

    // Retrofit
    api(Libs.RETROFIT)
    api(Libs.OKHTTP3_LOGGING_INTERCEPTOR)

    // Moshi
    implementation(Libs.RETROFIT_CONVERTER_MOSHI)
    implementation(Libs.MOSHI)
    implementation(Libs.MOSHI_KOTLIN)
    kapt(Libs.MOSHI_KOTLIN_CODEGEN)


    testImplementation(project(ModuleDependency.LIBRARY_TEST_UTILS))
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)
    testImplementation(Libs.MOCKK)
    testImplementation(Libs.ARCH)
    testImplementation(Libs.COROUTINES_CORE)
    testImplementation(Libs.COROUTINES_TEST)
    testImplementation(Libs.KLUENT)
    testImplementation(Libs.JUPITER_API)
    testRuntimeOnly(Libs.JUPITER_ENGINE)
}