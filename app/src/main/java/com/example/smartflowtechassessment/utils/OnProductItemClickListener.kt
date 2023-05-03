package com.example.smartflowtechassessment.utils

import com.example.smartflowtechassessment.model.MakeUpProductsItem

/**
 * Interface definition for a callback to be invoked when a makeup product item is clicked.
 */
interface OnProductItemClickListener {
    /**
     * Called when a makeup product item is clicked.
     * @param makeupProduct The clicked makeup product item.
     */
    fun onProductItemClick(makeupProduct: MakeUpProductsItem)
}