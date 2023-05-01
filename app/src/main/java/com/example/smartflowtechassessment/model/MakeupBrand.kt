package com.example.smartflowtechassessment.model
import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class MakeupBrand(
    val brand: String?,
    val productTypes: @RawValue ArrayList<MakeUpProductType>
): Parcelable