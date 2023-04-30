package com.example.smartflowtechassessment.api

import com.example.smartflowtechassessment.model.MakeUpProducts
import retrofit2.Response
import retrofit2.http.GET

interface MakeUpProductsService {
    @GET("products.json")
    suspend fun getMakeUpProducts(): Response<MakeUpProducts>
}