package com.example.smartflowtechassessment.di

import com.example.smartflowtechassessment.api.MakeUpProductsService
import com.example.smartflowtechassessment.repository.MakeUpRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesApiRepository(makeUpProductsService: MakeUpProductsService): MakeUpRepositoryImp {
        return MakeUpRepositoryImp(makeUpProductsService)
    }
}