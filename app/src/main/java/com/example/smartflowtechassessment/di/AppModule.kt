package com.example.smartflowtechassessment.di

import android.content.Context
import com.example.smartflowtechassessment.di.MakeUpProductsApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    internal fun provideApplication(@ApplicationContext app: Context): MakeUpProductsApplication {
        return app as MakeUpProductsApplication
    }
}