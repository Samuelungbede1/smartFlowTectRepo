package com.example.smartflowtechassessment.repository

import com.example.smartflowtechassessment.api.MakeUpProductsService
import com.example.smartflowtechassessment.model.MakeUpProducts
import retrofit2.Response
import javax.inject.Inject

class MakeUpRepositoryImp @Inject constructor(
    private val makeUpProductsService: MakeUpProductsService
): MakeUpRepository {
    override suspend fun getMakeUpProducts(): Response<MakeUpProducts> {
        return makeUpProductsService.getMakeUpProducts()
    }
}