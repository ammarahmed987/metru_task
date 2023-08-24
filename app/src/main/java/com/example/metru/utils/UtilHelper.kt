package com.example.metru.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import javax.inject.Inject

class UtilHelper @Inject constructor(private val context: Context) {

    @SuppressLint("HardwareIds")
    fun getDeviceId(): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}