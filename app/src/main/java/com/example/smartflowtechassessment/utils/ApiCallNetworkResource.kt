package com.example.smartflowtechassessment.utils


/**
 * The ApiCallNetworkResource class is used to wrap the response from API
 * calls and return it to the caller as a single object that can be easily used to
 * display the appropriate message to the user, handle errors, or show loading indicators.
*/
 sealed class ApiCallNetworkResource<T>(
    val data : T? = null,
    val message : String? = null

){
    class Success<T>(message: String,data: T? = null) : ApiCallNetworkResource<T>(data,message)
    class Error<T>(message: String, data: T? = null) : ApiCallNetworkResource<T>(data,message)
    class Loading<T>: ApiCallNetworkResource<T>()

}
