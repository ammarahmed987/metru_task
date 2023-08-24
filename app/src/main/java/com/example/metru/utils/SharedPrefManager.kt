package com.example.metru.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class SharedPrefManager @Inject constructor(private val context: Context) {

    private val Key_Pref = "Key_Pref"
    private val USERNAME = "KEY_USERNAME"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key_Pref, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun setUsername(username: String){
        editor.putString(USERNAME, username);
        editor.apply();
    }

    fun getUsername(): String{
        return sharedPreferences.getString(USERNAME,"")!!
    }

    fun logout(): Boolean {
        editor.clear()
        editor.apply()
        return true
    }

    fun clear(): Boolean {
        editor.clear()
        editor.apply()
        return true
    }
}