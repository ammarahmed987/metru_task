package com.example.metru.modules

import com.example.metru.base.BaseDockFragment
import com.example.metru.fragment.CameraFragment
import com.example.metru.fragment.PlayRecordingFragment
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

    @ContributesAndroidInjector
    fun contributePlayRecordingFragment(): PlayRecordingFragment

}