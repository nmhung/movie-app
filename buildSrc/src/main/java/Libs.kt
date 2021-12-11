object LibsVersions {
    const val APPCOMPAT = "1.4.0"
    const val MATERIAL = "1.4.0"
    const val CORE_KTX = "1.7.0"
    const val CONSTRAINT_LAYOUT = "2.1.2"

    // Test
    const val JUNIT = "4.+"
    const val EXT_JUNIT = "1.1.3"
    const val ESPRESSO_CORE = "3.4.0"
}


object Libs {
    const val CORE_KTX = "androidx.core:core-ktx:${LibsVersions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${LibsVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${LibsVersions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibsVersions.CONSTRAINT_LAYOUT}"

    // Test
    const val JUNIT = "junit:junit:${LibsVersions.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${LibsVersions.EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${LibsVersions.ESPRESSO_CORE}"
}