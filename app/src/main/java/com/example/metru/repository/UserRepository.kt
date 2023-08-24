package com.example.metru.repository

import com.example.metru.network.Api
import com.example.metru.utils.SharedPrefManager
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: Api,
    private val sharedPrefManager: SharedPrefManager
) : BaseRepository() {


}