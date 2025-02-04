package com.example.videojocapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.videojocapp.R
import com.example.videojocapp.databinding.FragmentCreatorBinding


class CreatorFragment : Fragment(R.layout.fragment_creator) {

    private lateinit var binding: FragmentCreatorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreatorBinding.bind(view)

        binding.btnClose.setOnClickListener {
        }
    }
}
