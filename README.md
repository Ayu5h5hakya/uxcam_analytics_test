# Spector Analytics

My submission for the UXCam technical challenge.

## Demo Instructions
This sample SDK uses [Requestbin](https://pipedream.com/requestbin) to inspect incoming requests. I have used it as a mock Analytics Dashboard.
In order to visualize the requests, please got to [Requestbin](https://pipedream.com/requestbin) and follow the instructions to create a new request bin.
Then set the endpoint URL in `BASE_URL` located in `spector_analytics\src\main\java\com.app.uxcam.spector_analytics\Configuration`. 

The endpoint should have the following format : https://eo5wfr96dnev8q2.m.pipedream.net (THIS IS JUST AN EXAMPLE)

Also, due to the very nature of the WorkManager API, requests will appear in this dashboard after each 15 min interval.  15 minutes is the minimum interval set by the OS, longer intervals are permitted but anything smaller than 15 is not permitted.

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

