import kotlin.reflect.full.memberProperties

// "Module" means "subproject" in terminology of Gradle API.
// To be specific each "Android module" is a Gradle "subproject"
@Suppress("unused")
object ModuleDependency {
    // All consts are accessed via reflection
    const val APP = ":app"
    const val DATA = ":data"
    const val DOMAIN = ":domain"
}
