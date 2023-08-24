package com.example.metru.modules

import com.example.metru.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *  @author Abdullah Nagori
 */

@Module
interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeDockActivity(): DockActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeWelcomeLoginActivity(): WelcomeLoginActivity
}