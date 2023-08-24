package com.example.metru.room

import com.example.metru.repository.BaseRepository
import javax.inject.Inject

class RoomHelper @Inject constructor(private val daoAccess: DAOAccess, private val ablDatabase: ABLDatabase) : BaseRepository() {

}