package com.example.metru.modules

import com.example.metru.base.BaseDockFragment
import com.example.metru.fragment.blankFragment.CameraFragment
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
    fun contributeCameraFragment(): CameraFragment

}