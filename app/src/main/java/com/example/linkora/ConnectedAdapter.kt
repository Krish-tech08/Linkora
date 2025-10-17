package com.example.linkora

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.linkora.data.Profile
import com.example.linkora.databinding.ItemConnectedProfileBinding


class ConnectedAdapter : ListAdapter<Profile, ConnectedAdapter.ViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemConnectedProfileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemConnectedProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: Profile) {
            binding.apply {
                profileImage.setImageResource(profile.imageResId)
                profileName.text = profile.name
                profileAge.text = profile.age
                profileBio.text = profile.bio
            }
        }
    }

    class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Profile, newItem: Profile) = oldItem == newItem
    }
}