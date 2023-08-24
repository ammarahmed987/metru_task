package com.example.metru.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.metru.constant.Constants
import com.google.gson.GsonBuilder

object SharedPrefKeyManager {


    //Shared Preference field used to save and retrieve JSON string
     lateinit var preferences: SharedPreferences


    fun with(context: Context) {
        preferences = context.getSharedPreferences(
            Constants.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        preferences.edit().putString(key, jsonString).apply()
    }


    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }


    fun clearALLData(){
        preferences.edit().clear().apply()
    }

}