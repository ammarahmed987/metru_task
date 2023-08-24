package com.example.metru.modules

import com.example.metru.room.ABLDatabase
import com.example.metru.room.DAOAccess
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Module
object AppDaoModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMyDao(myDB: ABLDatabase): DAOAccess {
        return myDB.leadDao()
    }
}