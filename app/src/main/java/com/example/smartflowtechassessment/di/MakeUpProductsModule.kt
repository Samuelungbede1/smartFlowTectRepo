package com.example.smartflowtechassessment.di
import com.example.smartflowtechassessment.api.MakeUpProductsService
import com.example.smartflowtechassessment.constants.BASE_URL
import com.example.smartflowtechassessment.repository.MakeUpRepository
import com.example.smartflowtechassessment.repository.MakeUpRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MakeUpProductsModule {

    @Singleton
    @Provides
    fun provideDataInMakeUpProductsModule(): MakeUpProductsService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MakeUpProductsService::class.java)

    }


    @Provides
    @Singleton
    fun providesMakeUpRepository(makeUpProductsService: MakeUpProductsService): MakeUpRepository {
        return MakeUpRepositoryImp(makeUpProductsService)
    }
}