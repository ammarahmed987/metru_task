package com.example.metru.viewModel

import androidx.lifecycle.ViewModel
import com.example.metru.network.ApiListener
import com.example.metru.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var apiListener: ApiListener? = null


}