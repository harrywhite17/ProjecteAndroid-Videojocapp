package com.example.videojocapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.videojocapp.R
import com.example.videojocapp.databinding.FragmentDetailBinding
import com.example.videojocapp.ui.viewmodel.VideojocViewModel
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val videojocViewModel: VideojocViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        // Load the videojoc details
        loadVideojocDetails()
    }

    private fun loadVideojocDetails() {
        // Get the videojoc by ID
        videojocViewModel.getVideojocById(args.videojocId.toInt())

        // Observe the videojoc LiveData
        videojocViewModel.videojoc.observe(viewLifecycleOwner) { videojoc ->
            videojoc?.let {
                binding.textViewName.text = it.name
                binding.textViewReleaseDate.text = it.releaseDate
                binding.textViewDescription.text = it.description
                binding.textViewRating.text = it.rating.toString()
                binding.textViewPlatform.text = it.platform
                binding.textViewYear.text = it.year.toString()
            }
        }
    }
}