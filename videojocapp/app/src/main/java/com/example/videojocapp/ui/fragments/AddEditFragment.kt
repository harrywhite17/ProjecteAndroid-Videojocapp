package com.example.videojocapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.videojocapp.R
import com.example.videojocapp.databinding.FragmentAddEditBinding
import com.example.videojocapp.data.model.Videojoc
import com.example.videojocapp.ui.viewmodel.VideojocViewModel

class AddEditFragment : Fragment(R.layout.fragment_add_edit) {

    private lateinit var binding: FragmentAddEditBinding
    private val videojocViewModel: VideojocViewModel by viewModels()
    private val args: AddEditFragmentArgs by navArgs()
    private var currentVideojoc: Videojoc? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddEditBinding.bind(view)

        // Load existing videojoc if editing
        if (args.videojocId != 0L) {
            loadVideojoc(args.videojocId.toInt())
        }

        binding.btnSave.setOnClickListener {
            saveVideojoc()
        }
    }

    private fun loadVideojoc(id: Int) {
        videojocViewModel.getVideojocById(id)
        videojocViewModel.videojoc.observe(viewLifecycleOwner) { videojoc ->
            videojoc?.let {
                currentVideojoc = it
                binding.editTextName.setText(it.name)
                binding.editTextReleaseDate.setText(it.releaseDate)
                binding.editTextDescription.setText(it.description)
                binding.editTextRating.setText(it.rating.toString())
                binding.editTextPlatform.setText(it.platform)
                binding.editTextYear.setText(it.year.toString())
            }
        }
    }

    private fun saveVideojoc() {
        val name = binding.editTextName.text.toString()
        val releaseDate = binding.editTextReleaseDate.text.toString()
        val description = binding.editTextDescription.text.toString()
        val rating = binding.editTextRating.text.toString().toFloatOrNull() ?: 0f
        val platform = binding.editTextPlatform.text.toString()
        val year = binding.editTextYear.text.toString().toIntOrNull() ?: 0

        if (name.isBlank() || releaseDate.isBlank() || description.isBlank() || platform.isBlank() || year == 0) {
            Toast.makeText(context, getString(R.string.please_fill_all_the_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val videojoc = if (currentVideojoc != null) {
            // Update existing videojoc
            currentVideojoc!!.copy(
                name = name,
                releaseDate = releaseDate,
                description = description,
                rating = rating,
                platform = platform,
                year = year
            )
        } else {
            // Create new videojoc
            Videojoc(
                name = name,
                releaseDate = releaseDate,
                description = description,
                rating = rating,
                platform = platform,
                year = year
            )
        }

        if (currentVideojoc != null) {
            videojocViewModel.updateVideojoc(videojoc)
        } else {
            videojocViewModel.addVideojoc(videojoc)
        }

        findNavController().navigateUp()
    }
}