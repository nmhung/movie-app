import com.android.build.gradle.internal.dsl.BaseFlavor
import com.android.build.gradle.internal.dsl.DefaultConfig

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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    // Each feature module that is included in settings.gradle.kts is added here as dynamic feature
    setDynamicFeatures(ModuleDependency.getFeatureModules().toMutableSet())
}


kapt {
    correctErrorTypes = true
}

dependencies {
    api(Libs.CORE_KTX)
    api(Libs.APPCOMPAT)
    api(Libs.MATERIAL)
    api(Libs.CONSTRAINT_LAYOUT)
    api(Libs.COROUTINES)
    api(Libs.SWIPE_REFRESH_LAYOUT)

    // Hilt
    api(Libs.HILT)
    kapt(Libs.HILT_ANDROID_COMPILER)
    kapt(Libs.HILT_COMPILER)

    // Retrofit
    api(Libs.RETROFIT)
    api(Libs.OKHTTP3_LOGGING_INTERCEPTOR)

    // Moshi
    api(Libs.RETROFIT_CONVERTER_MOSHI)
    api(Libs.MOSHI)
    api(Libs.MOSHI_KOTLIN)
    kapt(Libs.MOSHI_KOTLIN_CODEGEN)

    // ViewModel
    api(Libs.LIFECYCLE_VIEWMODEL_KTX)
    api(Libs.ACTIVITY_KTX)

    // LiveData
    api(Libs.LIFECYCLE_LIVEDATA_KTX)
    api(Libs.LIFECYCLE_KTX)
    api(Libs.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    kapt(Libs.LIFECYCLE_COMPILER)

    api(Libs.TIMBER)

    // Navigation
    api(Libs.NAVIGATION_FRAGMENT_KTX)
    api(Libs.NAVIGATION_UI_KTX)
    api(Libs.NAVIGATION_KTX)
    implementation(Libs.NAVIGATION_DYNAMIC_FEATURE_FRAGMENT)

    // Epoxy
    api(Libs.EPOXY)
    api(Libs.EPOXY_DATABINDING)
    kapt(Libs.EPOXY_PROCESSOR)

    // Glide
    api(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)


    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)
}

/*
Takes value from Gradle project property and sets it as build config property
 */
fun BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

/*
Return names of the features
 */
fun getFeatureNames() = ModuleDependency.getFeatureModules()
    .map { it.replace(":feature_", "") }
    .toSet()

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

/*
Adds a new field to the generated BuildConfig class
 */
fun DefaultConfig.buildConfigField(name: String, value: Set<String>) {
    // Create String that holds Java String Array code
    val strValue = value.joinToString(prefix = "{", separator = ",", postfix = "}", transform = { "\"$it\"" })
    buildConfigField("String[]", name, strValue)
}
