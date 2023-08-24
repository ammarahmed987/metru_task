package com.example.metru.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.metru.keys.ViewModelKey
import com.example.metru.viewModel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *  @author Abdullah Nagori
 */

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun bindBaseViewModel(baseViewModel: BaseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

}