package com.okta.subeventappdicoding

import android.annotation.SuppressLint
import com.okta.subeventappdicoding.databinding.FragmentFavoriteBinding
import com.okta.subeventappdicoding.model.Event
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okta.subeventappdicoding.model.FavoriteEvent

class FavoriteAdapter : ListAdapter<FavoriteEvent, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//        holder.bind(getItem(position))
    }

    class FavoriteViewHolder(private val binding: FragmentFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            with(binding) {
                tvEventName.text = event.name
                tvEventSummary.text = event.summary
                tvEventLocation.text = event.cityName
                tvEventDate.text = event.beginTime
                tvEventQuota.text = "${event.registrants}/${event.quota}"

                Glide.with(itemView.context)
                    .load(event.imageLogo)
                    .into(ivEventImage)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEvent>() {
            override fun areItemsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
                return oldItem.eventId == newItem.eventId
            }

            override fun areContentsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
                return oldItem == newItem
            }
        }
    }
}