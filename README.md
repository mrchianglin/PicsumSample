# Android Take Home

## Build tools and versions used
* Kotlin: 2.0.0
* Gradle: 8.9.0
* Android Gradle Plugin: 8.7.1
* Jetpack Compose BoM: 2024.01.01
* Min SDK: 27
* Target SDK: 35

## Project Structure
Within com.example.android.picsumsample directory, files are sorted into
- data : Any networking files
- feature : Feature related files like View Models or any Composables
- ui.theme

## Steps to run the app
Ensure your SDK location is defined with an `ANDROID_SDK_ROOT` environment variable or set sdk.dir path in the project's local properties file at `android/local.properties` for the following Gradle commands to function. `Sync Project with Gradle Files` in Android Studio is a common solution.

1. Build debug APK
   `
   ./gradlew assembleDebug
   `
1. Install debug APK
   `
   ./gradlew installDebug
   `
1. Run app on emulator or physical device with Android API 27 or higher.

## Running unit tests
To run unit tests, run the following command, depending on your computer OS:

### macOS

`
./gradlew :app:cleanTestDebugUnitTest :app:testDebugUnitTest --tests "com.example.android.resytakehomecompose.*"
`

### Windows

`
gradlew :app:cleanTestDebugUnitTest :app:testDebugUnitTest --tests "com.example.android.resytakehomecompose.*"
`

## What areas of the app did you focus on?
The primary focus areas of this app were:
1. Implementing the UI with Jetpack Compose
2. Implementing the app with clean architecture

## What was the reason for your focus? What problems were you trying to solve?
The main goal was to provide an efficient, intuitive interface for browsing a list of photos by leveraging Jetpack Compose’s declarative UI. This aimed to offer seamless performance and reusability improvements over the traditional Android Views in XML.

Improving the UX allows the app to be intuitive and easy to use.

Writing the codebase in a clean architecture allows reusability of components, improves testability, introduces separation of concerns. Not only does this improve the current code quality and developer confidence, but it also allows more features to be added in the future easily.

## Note
The [ResyResult](https://github.com/mrchianglin/AMEX-Takehome/blob/main/app/src/main/java/com/example/android/resytakehomecompose/data/ResyResult.kt) class is a copy on Mavericks' [Async class](https://airbnb.io/mavericks/#/async).

In addition to the Build tools & versions mentioned earlier, the project uses other open-source libraries and dependencies such as:
- Hilt: 2.51.1
- AndroidX libraries
- Coil: 3.0.0-rc02
- OkHttp: 4.12.0
- Retrofit: 2.11.0
- Mockk: 1.13.13
