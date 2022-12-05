## About app

1. Manages dependencies using Gradle's [**version catalog and toml**](https://docs.gradle.org/current/userguide/platforms.html) config.
2. Android [Splash Screen](https://developer.android.com/guide/topics/ui/splash-screen) API
3. [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
4. [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) using Fragments
5. [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData) & [ViewModel](https://developer.android.com/reference/android/arch/lifecycle/ViewModel)
6. [Dagger2](https://developer.android.com/training/dependency-injection/dagger-basics) + [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection
7. [Retrofit](https://square.github.io/retrofit/)
8. [Room](https://developer.android.com/training/data-storage/room)
9. [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview), [PagingDataAdapter](https://developer.android.com/reference/kotlin/androidx/paging/PagingDataAdapter), [LoadStateAdapter](https://developer.android.com/reference/kotlin/androidx/paging/LoadStateAdapter)
10. [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview), [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
11. [GSON](https://github.com/google/gson)
12. [Kotlin Flow](https://developer.android.com/kotlin/flow)
13. [Material Design](https://material.io/develop/android)
14. [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
15. Clean code with Tests
16. Search tried - but not completed fully ,because lage amount of data means we can use search api ,in database its not possible ,it goes to ANR/crash.

## Config

Add HackerNews API's baseUrl in `local.properties` file ,use this below url in build file to call api
```properties
hn.baseUrl=https://hacker-news.firebaseio.com/
hn.apiVersion=v0/
```
