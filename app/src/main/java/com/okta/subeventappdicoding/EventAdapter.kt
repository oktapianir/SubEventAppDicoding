package com.okta.subeventappdicoding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.okta.subeventappdicoding.databinding.ItemEventBinding
import com.okta.subeventappdicoding.model.Event
import java.text.SimpleDateFormat
import java.util.Locale

class EventAdapter(
    private val onItemClick: (Event) -> Unit
) : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        if (event != null) {
            holder.bind(event)
        }
    }

    inner class EventViewHolder(
        private val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    getItem(position)?.let { event ->
                        onItemClick(event)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(event.imageLogo)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivEventImage)

                tvEventName.text = event.name
                tvEventDate.text = formatDate(event.beginTime)

                val remainingQuota = (event.quota - event.registrants).coerceAtLeast(0)
                tvEventQuota.apply {
                    text = "$remainingQuota slots left"
                    isVisible = remainingQuota > 0
                }
            }
        }

        private fun formatDate(dateString: String?): String {
            return try {
                // Input format sesuai dengan API
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

                // Output format untuk ditampilkan ke pengguna
                val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

                // Parse dan format ulang
                val date = inputFormat.parse(dateString ?: "")
                if (date != null) {
                    outputFormat.format(date)
                } else {
                    "Invalid date"
                }
            } catch (e: Exception) {
                "Invalid date"
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}