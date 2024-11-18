# Spector Analytics

My submission for the UXCam technical challenge.

## SDK Dependencies
- Koin
- Retrofit
- WorkManager
- ViewModel
- Coroutine
- Mockk

## Setup and Installation
- In `app/build.gradle`:
```[Kotlin]
  dependencies {
    ...
    implementation(project(":spector_analytics"))
    ...
  }
```
- Create an Application class. Steps available [here](https://guides.codepath.com/android/Understanding-the-Android-Application-Class)
- Make the following edits in the `onCreate` method of your `Application` class
```
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this)
            workManagerFactory()
            modules(spectorModule)
            ...
        }
    }
```
- Initialize a globally accessible object to access the SDK. The sample app for instance, uses a Koin singleton.
```
  single { SpectorAnalytics() }
```

## Features
- Room is used to store all tracked events
- WorkManager sends all the queued events to the remote endpoint every 15 minutes
- `start()` Queue a start event in the Room database
- `logEvent(name : String, data : Map<String, String>)` : Queue a custom event with `name` and optional `data` event parameters
- `endSession` Queue a end event in the Room database

