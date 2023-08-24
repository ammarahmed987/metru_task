package com.example.metru.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metru.constant.Constants
import com.example.metru.model.DummyModel

@Database(entities = [DummyModel::class], version = 1, exportSchema = false)
abstract class ABLDatabase : RoomDatabase() {

    abstract fun leadDao(): DAOAccess

    companion object {


        const val DATABASE_NAME = Constants.DATABASE_NAME

        @Volatile // All threads have immediate access to this property
        private var instance: ABLDatabase? = null

        private val LOCK = Any() // Makes sure no threads making the same thing at the same time

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ABLDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun getInstance(activityContext: Context): ABLDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(activityContext.applicationContext).also { instance = it }
        }
    }
}