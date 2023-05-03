package com.example.smartflowtechassessment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This is the AppModule class that is responsible for providing the
// application context to the dependency graph using Dagger Hilt.
// It is annotated with @Module and @InstallIn to specify that it belongs
// to the SingletonComponent and provides a single instance of MakeUpProductsApplication.
// The provideApplication method is annotated with @Singleton to ensure that only one instance of
// MakeUpProductsApplication is created throughout the app's lifecycle.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    internal fun provideApplication(@ApplicationContext app: Context): MakeUpProductsApplication {
        return app as MakeUpProductsApplication
    }
}