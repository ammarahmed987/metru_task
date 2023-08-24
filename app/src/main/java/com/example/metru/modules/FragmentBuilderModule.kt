package com.example.metru.modules

import com.example.metru.base.BaseDockFragment
import com.example.metru.fragment.*
import com.example.metru.fragment.blankFragment.BlankFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 *  @author Abdullah Nagori
 */

@Module
interface
FragmentBuilderModule {

    @ContributesAndroidInjector
    fun contributeBaseFragment(): BaseDockFragment

    @ContributesAndroidInjector
    fun contributeWelcomeLoginFragment(): WelcomeLoginFragment

    @ContributesAndroidInjector
    fun contributeBlankFragment(): BlankFragment

}