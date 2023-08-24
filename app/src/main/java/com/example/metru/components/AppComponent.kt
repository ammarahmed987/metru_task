package com.example.metru.components

import android.app.Application
import com.example.metru.App
import com.example.metru.modules.ActivityBuilderModule
import com.example.metru.modules.AppDaoModule
import com.example.metru.modules.AppDbModule
import com.example.metru.modules.ApplicationContextModule
import com.example.metru.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    AppDbModule::class,
    AppDaoModule::class,
    ActivityBuilderModule::class,
    ApplicationContextModule::class])

interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}