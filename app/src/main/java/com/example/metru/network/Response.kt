package com.example.metru.network

open class Response {
    object Loading : Response()
    data class Success<T>(val data: T) : Response()
    data class Error(val exception: String) : Response()
}