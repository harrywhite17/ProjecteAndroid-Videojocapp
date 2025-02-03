package com.example.videojocapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videojocapp.R
import com.example.videojocapp.ui.adapter.VideojocAdapter
import com.example.videojocapp.data.model.Videojoc
import com.example.videojocapp.databinding.FragmentHomeBinding
import com.example.videojocapp.ui.viewmodel.VideojocViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val videojocViewModel: VideojocViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        // Set up the RecyclerView with the VideojocAdapter
        val videojocAdapter = VideojocAdapter(
            onEditClick = { videojoc -> editVideojoc(videojoc) },
            onViewClick = { videojoc -> viewVideojoc(videojoc) },
            onDeleteClick = { videojoc -> deleteVideojoc(videojoc) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = videojocAdapter

        // Observe the LiveData from ViewModel and update the RecyclerView
        videojocViewModel.allVideojocs.observe(viewLifecycleOwner, Observer { videojocs ->
            videojocAdapter.submitList(videojocs)  // Update the list in the RecyclerView
        })

        // FAB to navigate to Add/Edit Fragment
        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddEdit(0L) // Pass 0L for new game
            findNavController().navigate(action)
        }

        // Set up the Creator Info button
        binding.btnCreatorInfo.setOnClickListener {
            showCreatorInfoDialog()
        }
    }

    private fun showCreatorInfoDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Creator Information")
            .setMessage("This app was created by Harry White.\nContact: harrywhite@iesebre.com")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun editVideojoc(videojoc: Videojoc) {
        val action = HomeFragmentDirections.actionHomeToAddEdit(videojoc.id.toLong()) // Pass the id of the game to edit
        findNavController().navigate(action)
    }

    private fun viewVideojoc(videojoc: Videojoc) {
        val action = HomeFragmentDirections.actionHomeToDetail(videojoc.id.toLong()) // Pass the id to view details
        findNavController().navigate(action)
    }

    private fun deleteVideojoc(videojoc: Videojoc) {
        // Show confirmation dialog before deleting
        AlertDialog.Builder(requireContext())
            .setMessage("Are you sure you want to delete this video game?")
            .setPositiveButton("Yes") { _, _ ->
                videojocViewModel.deleteVideojoc(videojoc)  // Call delete function in ViewModel
            }
            .setNegativeButton("No", null)
            .show()
    }
}