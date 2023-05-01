package com.example.smartflowtechassessment.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartflowtechassessment.model.MakeUpProductType
import com.example.smartflowtechassessment.model.MakeUpProductsItem
import com.example.smartflowtechassessment.model.MakeupBrand
import com.example.smartflowtechassessment.repository.MakeUpRepository
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MakeUpProductsViewModel @Inject constructor(private val makeUpRepositoryInterface: MakeUpRepository) : ViewModel() {

    private val _makeUpProductList: MutableLiveData<ApiCallNetworkResource<ArrayList<MakeupBrand>>> =
        MutableLiveData()
    val makeUpProductList: LiveData<ApiCallNetworkResource<ArrayList<MakeupBrand>>> =
        _makeUpProductList


    fun getMakeUpProducts() {
        _makeUpProductList.value = ApiCallNetworkResource.Loading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = makeUpRepositoryInterface.getMakeUpProducts()
                if (response.isSuccessful) {
                    val makeUpProducts = response.body()!!
                    val deferredMakeupBrands =
                        async { groupMakeUpProductsByBrandAndProductType(makeUpProducts) }
                    val makeupBrands = deferredMakeupBrands.await()
                    val transformedResource =
                        ApiCallNetworkResource.Success("Success", makeupBrands)
                    withContext(Dispatchers.Main) {
                        _makeUpProductList.value = transformedResource
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error occurred"
                    val transformedResource =
                        ApiCallNetworkResource.Error<ArrayList<MakeupBrand>>(errorMessage)
                    withContext(Dispatchers.Main) {
                        _makeUpProductList.value = transformedResource
                    }
                }
            } catch (e: Exception) {
                val transformedResource = ApiCallNetworkResource.Error<ArrayList<MakeupBrand>>(
                    e.message ?: "Unknown error occurred"
                )
                withContext(Dispatchers.Main) {
                    _makeUpProductList.value = transformedResource
                }
            }
        }
    }

    private fun groupMakeUpProductsByBrandAndProductType(products: List<MakeUpProductsItem>): ArrayList<MakeupBrand> {
        return products.groupBy { it.brand }
            .map { (brandName, items) ->
                val makeupProducts = items.groupBy { it.product_type }
                    .map { (productTypeName, products) ->
                        MakeUpProductType(
                            productTypeName,
                            products as ArrayList<MakeUpProductsItem>
                        )
                    }
                MakeupBrand(brandName, makeupProducts as ArrayList<MakeUpProductType>)
            } as ArrayList<MakeupBrand>
    }
}