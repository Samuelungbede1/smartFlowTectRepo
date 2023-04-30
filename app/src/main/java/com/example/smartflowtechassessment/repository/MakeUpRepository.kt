package com.example.smartflowtechassessment.repository

import com.example.smartflowtechassessment.model.MakeUpProducts
import retrofit2.Response

interface MakeUpRepository {
    suspend fun getMakeUpProducts (): Response<MakeUpProducts>
}