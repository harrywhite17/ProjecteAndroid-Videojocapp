package com.example.videojocapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videojoc")
data class Videojoc(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val platform: String,
    val year: Int,
    val releaseDate: String,
    val description: String,
    val rating: Float
)