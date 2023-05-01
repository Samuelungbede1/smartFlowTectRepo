package com.example.smartflowtechassessment.utils

import com.example.smartflowtechassessment.model.MakeUpProductsItem

interface OnProductItemClickListener {
    fun onProductItemClick(makeupProduct: MakeUpProductsItem)
}