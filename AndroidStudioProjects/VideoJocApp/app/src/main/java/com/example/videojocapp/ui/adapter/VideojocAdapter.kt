package com.example.videojocapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.videojocapp.data.model.Videojoc
import com.example.videojocapp.databinding.ItemVideojocBinding

class VideojocAdapter(
    private val onEditClick: (Videojoc) -> Unit,
    private val onViewClick: (Videojoc) -> Unit,
    private val onDeleteClick: (Videojoc) -> Unit
) : ListAdapter<Videojoc, VideojocAdapter.VideojocViewHolder>(VideojocDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideojocViewHolder {
        val binding = ItemVideojocBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideojocViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideojocViewHolder, position: Int) {
        val videojoc = getItem(position)
        holder.bind(videojoc)
    }

    inner class VideojocViewHolder(private val binding: ItemVideojocBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videojoc: Videojoc) {
            binding.textViewName.text = videojoc.name   // Bind name
            binding.textViewRating.text = videojoc.rating.toString()  // Bind rating

            // Set up button click listeners
            binding.btnView.setOnClickListener {
                onViewClick(videojoc)  // Trigger the View action
            }

            binding.btnEdit.setOnClickListener {
                onEditClick(videojoc)  // Trigger the Edit action
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(videojoc)  // Trigger the Delete action
            }
        }
    }

    class VideojocDiffCallback : DiffUtil.ItemCallback<Videojoc>() {
        override fun areItemsTheSame(oldItem: Videojoc, newItem: Videojoc): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Videojoc, newItem: Videojoc): Boolean {
            return oldItem == newItem
        }
    }
}
