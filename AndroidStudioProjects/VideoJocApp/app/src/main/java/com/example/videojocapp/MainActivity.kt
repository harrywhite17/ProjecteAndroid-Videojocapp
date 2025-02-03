package com.example.videojocapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.videojocapp.R
import com.example.videojocapp.data.model.Videojoc
import com.example.videojocapp.ui.viewmodel.VideojocViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: VideojocViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the ViewModel
        viewModel = ViewModelProvider(this)[VideojocViewModel::class.java]

        // Observe the LiveData
        viewModel.allVideojocs.observe(this) { videojocs ->
            // Update your UI with the list of videojocs
            // For example, update a RecyclerView adapter
            println("Videojocs: $videojocs")
        }

        // Example of adding a new Videojoc
        val newVideojoc = Videojoc(
            name = "New Game",
            platform = "PC",
            year = 2023,
            releaseDate = "2023-01-01",
            description = "A new exciting game",
            rating = 4.5F
        )
        viewModel.addVideojoc(newVideojoc)
    }
}