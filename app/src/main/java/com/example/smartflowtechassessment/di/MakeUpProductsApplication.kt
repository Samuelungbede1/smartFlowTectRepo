package com.example.smartflowtechassessment.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

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