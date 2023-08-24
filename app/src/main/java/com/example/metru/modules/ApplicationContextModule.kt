package com.example.metru.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Module(includes = [AndroidInjectionModule::class])
object ApplicationContextModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


}