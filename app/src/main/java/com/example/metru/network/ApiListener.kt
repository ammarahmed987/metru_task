package com.example.metru.network

import androidx.lifecycle.LiveData

/**
 * @author Abdullah Nagori
 */

interface ApiListener {
    fun onStarted();
    fun onSuccess(liveData: LiveData<String>, tag:String)
    fun onFailure(message:String,tag:String)
    fun onFailureWithResponseCode(code: Int,message:String,tag: String)
}