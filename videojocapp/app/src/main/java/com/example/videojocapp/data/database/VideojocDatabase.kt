package com.example.videojocapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.videojocapp.data.dao.VideojocDao
import com.example.videojocapp.data.model.Videojoc

@Database(entities = [Videojoc::class], version = 1, exportSchema = false)
abstract class VideojocDatabase : RoomDatabase() {

    abstract fun videojocDao(): VideojocDao

    companion object {
        @Volatile
        private var INSTANCE: VideojocDatabase? = null

        fun getDatabase(context: Context): VideojocDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideojocDatabase::class.java,
                    "videojoc_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}