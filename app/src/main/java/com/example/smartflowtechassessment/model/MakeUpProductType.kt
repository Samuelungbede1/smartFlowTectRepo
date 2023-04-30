package com.example.smartflowtechassessment.model

import com.example.smartflowtechassessment.model.MakeUpProductsItem

data class MakeUpProductType(
    val productType: String,
    val products: List<MakeUpProductsItem>
)