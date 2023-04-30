package com.example.smartflowtechassessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartflowtechassessment.model.MakeUpProducts
import com.example.smartflowtechassessment.repository.MakeUpRepository
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MakeUpProductsViewModel @Inject constructor(private val makeUpRepositoryInterface: MakeUpRepository): ViewModel() {

    private val _makeUpProductList: MutableLiveData<ApiCallNetworkResource<MakeUpProducts>> = MutableLiveData()
    val makeUpProductList: LiveData<ApiCallNetworkResource<MakeUpProducts>> = _makeUpProductList
//    val makeUpProductList = MutableLiveData<ApiCallNetworkResource<MakeUpProducts>>()

//    fun getMakeUpProducts() {
//        viewModelScope.launch {
//            _makeUpProductList.postValue(ApiCallNetworkResource.Loading())
//            try {
//                delay(1)
//                val response = makeUpRepositoryInterface.getMakeUpProducts()
//                if (response.isSuccessful) {
//                    val makeUpProducts = response.body() ?: emptyList()
//                    val makeupBrands = makeUpProducts.groupBy <MakeUpProduct, String>{ it.brand }
//                        .map { (brandName, items) ->
//                            val makeupProducts = items.groupBy { it.product_type }
//                                .map { (productTypeName, products) ->
//                                    MakeUpProductType(productTypeName, products)
//                                }
//                            MakeupBrand(brandName, makeupProducts)
//                        }
//                    _makeUpProductList.postValue(ApiCallNetworkResource.Success("Done", makeupBrands))
//                } else {
//                    _makeUpProductList.postValue(ApiCallNetworkResource.Error(response.message()))
//                }
//            } catch (e: Throwable) {
//                e.printStackTrace()
//                when (e) {
//                    is IOException -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                message = "Network Failure, please check your internet connection"
//                            )
//                        )
//                    }
//                    is NullPointerException -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                "Invalid Request, please try again"
//                            )
//                        )
//                    }
//                    else -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                message = "an error occur please try again later"
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }





//    fun getMakeUpProducts() {
//        viewModelScope.launch {
//            _makeUpProductList.postValue(ApiCallNetworkResource.Loading())
//            try {
//                delay(1)
//                val response = makeUpRepositoryInterface.getMakeUpProducts()
//                if (response.isSuccessful) {
//                    val makeupBrands = response.body()?.groupBy { it[0].brand }
//                        ?.map { (brand, items) ->
//                            val makeupProducts = items.groupBy { it[0].product_type }
//                                .map { (product_type, products) ->
//                                    MakeUpProductType(product_type, products)
//                                }
//                            MakeupBrand(brand, makeupProducts)
//                        }
//
//                    _makeUpProductList.postValue(
//                        ApiCallNetworkResource.Success(
//                            response.message(),
//                            makeupBrands
//                        )
//                    )
//
//                } else {
//                    _makeUpProductList.postValue(ApiCallNetworkResource.Error(response.message()))
//                }
//            } catch (e: Throwable) {
//                e.printStackTrace()
//                when (e) {
//                    is IOException -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                message =
//                                "Network Failure, please check your internet connection"
//                            )
//                        )
//                    }
//                    is NullPointerException -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                "Invalid Request, please try again"
//                            )
//                        )
//                    }
//                    else -> {
//                        _makeUpProductList.postValue(
//                            ApiCallNetworkResource.Error(
//                                message =
//                                "An error occurred, please try again later"
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }


    /**Handling Network Error*/
    fun getMakeUpProducts () {
        viewModelScope.launch {
            _makeUpProductList.postValue(ApiCallNetworkResource.Loading())
            try {
                delay(1)
                val response = makeUpRepositoryInterface.getMakeUpProducts()
                if (response.isSuccessful) {

                    _makeUpProductList.postValue(
                        ApiCallNetworkResource.Success(
                            response.message(),
                            response.body()
                        )
                    )

//
                }else
                {
                    _makeUpProductList.postValue(ApiCallNetworkResource.Error(response.message()))
                }

            } catch (e: Throwable) {
                e.printStackTrace()
                when(e){
                    is IOException ->{
                        _makeUpProductList.postValue(
                            ApiCallNetworkResource.Error(
                                message =
                                "Network Failure, please check your internet connection"
                            )
                        )
                    }
                    is NullPointerException ->{
                        _makeUpProductList.postValue(
                            ApiCallNetworkResource.Error(
                                "Invalid Request, please try again"
                            )
                        )
                    }
                    else->{
                        _makeUpProductList.postValue(
                            ApiCallNetworkResource.Error(
                                message =
                                "an error occur please try again later"
                            )
                        )
                    }
                }
            }
        }
    }


}