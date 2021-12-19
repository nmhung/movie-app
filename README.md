# movie-app
The Movie App following best practices: Kotlin, Coroutines, JetPack, Clean Architecture, Tests, MVVM, DI, Static Analysis...


## Module structure

- #### App module
    [App module](/app) is the implementation of user interfaces on the application.
Based on mvvm architecture (view-databinding-viewmodel-model) with the repository pattern.

- #### Domain module

    [Domain module](/domain) composed of use cases of the app.

- #### Data module

    [Data module](/data) composed of handle database queries and networking requests.




 ## Tech-stack
   - [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - perform background operations
   - [Retrofit](https://square.github.io/retrofit/) - networking
   - Moshi for Json Parsing
   - [Jetpack](https://developer.android.com/jetpack)
        - [Navigation](https://developer.android.com/guide/navigation) - in-app navigation
        - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about data change
        - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way

  - [Glide](https://bumptech.github.io/glide/) - image loading library
- [Proxyman](https://proxyman.io/) - network proxy debugging
- [Hilt](https://dagger.dev/hilt) - constructing dependency injection
- Modern Architecture
    -  [Android Architecture components](https://developer.android.com/topic/architecture) (ViewModel, LiveData, Navigation)
    - [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
- UI
    - [Material Design](https://material.io/design)
    - [Material Theming Guide](https://material.io/develop/android/theming/theming-overview)
- Gradle
    - Plugins ([SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args))


## Set up
Need to put "MAPS_API_KEY" in your local.properties
## Run
Use Android Studio Arctic Fox stable.
1. File -> Sync Project with Gradle Files
2. Run -> Run 'app'

