package com.example.metru.network.coroutine

open class WebResponse {

    object Loading : WebResponse()
    data class Success<T>(val data: T) : WebResponse()
    data class Error(val exception: String) : WebResponse()

}