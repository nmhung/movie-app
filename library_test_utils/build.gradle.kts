plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID) // or kotlin("android") or id 'kotlin-android'
    id(GradlePluginId.ANDROID_JUNIT_5)
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

    testOptions {
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/licenses/**")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(Libs.MOCKK)
    implementation(Libs.ARCH)
    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_TEST)
    implementation(Libs.KLUENT)
    implementation(Libs.JUPITER_API)
    runtimeOnly(Libs.JUPITER_ENGINE)
}