package com.example.smartflowtechassessment.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// This is the Application class for the MakeUpProducts app.
// It is annotated with @HiltAndroidApp, which allows Hilt to generate a dependency graph for this app.
// The companion object has a synchronized getter and a private setter for the application instance.
// This class is responsible for initializing the application and setting the application instance.

@HiltAndroidApp
class MakeUpProductsApplication : Application() {
    companion object {
        @get:Synchronized
        lateinit var application: MakeUpProductsApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        application = this
    }

}