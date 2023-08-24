package com.example.metru.application

import android.app.Application
import com.example.metru.BuildConfig
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {


    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        // SharedPrefManager
      //  SharedPrefManager.with(this)

        //start Koin
        startKoin {
            androidContext(this@Application)

        }
    }
}