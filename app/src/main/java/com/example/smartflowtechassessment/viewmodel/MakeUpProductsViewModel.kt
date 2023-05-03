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

    /**
     * This private MutableLiveData variable is used to store and observe the API response of the makeup products list.
     * It is exposed as a LiveData variable 'makeUpProductList' to the ViewModel, so that it can be observed by the UI layer.
     * The ApiResponse is of type ArrayList<MakeupBrand> wrapped inside an ApiCallNetworkResource class.
    */
    private val _makeUpProductList: MutableLiveData<ApiCallNetworkResource<ArrayList<MakeupBrand>>> = MutableLiveData()
    val makeUpProductList: LiveData<ApiCallNetworkResource<ArrayList<MakeupBrand>>> = _makeUpProductList

    /**
     * A LiveData that stores the selected makeup product item, used to update the UI with details
     * of the selected product. The value can be set using the _makeupProductItem MutableLiveData.
     * The current value of this LiveData can be observed using the makeupProductItem LiveData.
    */
    private val _makeupProductItem : MutableLiveData<MakeUpProductsItem> = MutableLiveData()
     val makeupProductItem : MutableLiveData<MakeUpProductsItem> = _makeupProductItem


    /**
     * This function is responsible for making an API call to retrieve a list of makeup products,
     * grouping the products by brand and product type, and transforming the result into an instance of ApiCallNetworkResource.
     * The resulting resource is then set as the value of _makeUpProductList, which is observed by the UI to update the view accordingly.
     * The function is executed asynchronously using Coroutines to prevent blocking the UI thread,
     * and exceptions are caught and transformed into error resources.
    */
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

    /**
     * Groups the given list of makeup products by brand and product type,
     * returning an ArrayList of MakeupBrand objects
     * Each MakeupBrand contains a brand name and a list of MakeupProductType
     * objects, each of which represents a product type
     * and a list of MakeupProducts of that type.
    */
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



    /**
     * Sets the selected makeup product item and updates the [makeupProductItem] LiveData with the new value.
     * @param makeUpProductItem The selected makeup product item.
     */
    fun setSelectedMakeupProduct(makeUpProductItem: MakeUpProductsItem){
        _makeupProductItem.postValue(makeUpProductItem)
    }

}