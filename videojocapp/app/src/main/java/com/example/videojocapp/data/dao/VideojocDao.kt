package com.example.videojocapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.videojocapp.data.model.Videojoc
import kotlinx.coroutines.flow.Flow

@Dao
interface VideojocDao {
    @Insert
    fun insertVideojoc(videojoc: Videojoc?): Long

    @Update
    fun updateVideojoc(videojoc: Videojoc?): Int

    @Delete
    fun deleteVideojoc(videojoc: Videojoc?): Int

    @Query("SELECT * FROM videojoc WHERE id = :id")
    fun getVideojocById(id: Int): Flow<Videojoc?>?

    @get:Query("SELECT * FROM videojoc")
    val allVideojocs: Flow<List<Videojoc?>?>?
}