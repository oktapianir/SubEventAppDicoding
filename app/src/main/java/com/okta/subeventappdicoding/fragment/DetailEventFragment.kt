//package com.okta.subeventappdicoding.fragment
//
//import android.annotation.SuppressLint
//import android.content.ActivityNotFoundException
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.text.Html
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
//import com.okta.subeventappdicoding.R
//import com.okta.subeventappdicoding.EventViewModel
//import com.okta.subeventappdicoding.EventViewModelFactory
//import com.okta.subeventappdicoding.Repository.EventRepository
//import com.okta.subeventappdicoding.API.RetrofitClient
//import com.okta.subeventappdicoding.EventDatabase
//import com.okta.subeventappdicoding.databinding.FragmentDetailEventBinding
//import com.okta.subeventappdicoding.model.Event
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class DetailEventFragment : Fragment() {
//    private lateinit var viewModel: EventViewModel
//    private var _binding: FragmentDetailEventBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val database = EventDatabase.getDatabase(requireContext())
//        val FavoriteEventDao = database.favoriteEventDao()
//
//
//        val repository = EventRepository(RetrofitClient.api, FavoriteEventDao)
//        val factory = EventViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]
//
//        Log.d("DetailEventFragment", "Arguments: ${arguments?.keySet()}")
//
//        val event = arguments?.getParcelable<Event>("event")
//
//        if (event != null) {
//            Log.d("DetailEventFragment", "Received event: ${event.name}")
//            displayEventDetails(event)
//            viewModel.getEventDetail(event.id)
//        } else {
//            Log.e("DetailEventFragment", "No event passed to fragment")
//            Toast.makeText(requireContext(), "Error loading event details", Toast.LENGTH_SHORT)
//                .show()
//        }
//
//        val btnBack: ImageView = view.findViewById(R.id.btnBack)
//        btnBack.setOnClickListener {
//            findNavController().navigateUp()
//        }
////        viewModel.isFavorite(event.id).observe(viewLifecycleOwner) { isFavorite ->
////            binding.btnFavorite.setImageResource(
////                if (isFavorite) R.drawable.ic_favorite1
////                else R.drawable.ic_favorite
////            )
////        }
//        event?.let { currentEvent ->
//            viewModel.isFavorite(currentEvent.id).observe(viewLifecycleOwner) { isFavorite ->
//                binding.btnFavorite.setImageResource(
//                    if (isFavorite) R.drawable.ic_favorite1 else R.drawable.ic_favorite
//                )
//            }
//        }
//
//
//        // Setup favorite button click
//        binding.btnFavorite.setOnClickListener {
//            event?.let { currentEvent ->
//                viewModel.toggleFavorite(currentEvent)
//            }
//        }
//
////        event?.let { currentEvent ->
////            viewModel.isFavorite(currentEvent.id).observe(viewLifecycleOwner) { isFavorite ->
////                binding.btnFavorite.setImageResource(
////                    if (isFavorite) R.drawable.ic_favorite
////                    else R.drawable.ic_favorite
////                )
////            }
////
////            // Set click listener untuk toggle favorite
////            binding.btnFavorite.setOnClickListener {
////                viewModel.toggleFavorite(currentEvent)
////            }
////        }
//    }
//
//    private var eventLink: String? = null
//
//    @SuppressLint("SetTextI18n")
//    private fun displayEventDetails(event: Event) {
//        with(binding) {
//            tvEventName.text = event.name
//            tvEventOrganizer.text = "By ${event.ownerName}"
//            tvEventDate.text =
//                "Event Date: ${formatDate(event.beginTime)} - ${formatDate(event.endTime)}"
//            tvEventDescription.text = Html.fromHtml(event.description, Html.FROM_HTML_MODE_COMPACT)
////            tvEventDescription.text = event.description
//            tvEventQuota.text =
//                "Quota: ${event.quota} (Available: ${event.quota - event.registrants})"
//            tvEventLocation.text = "Location: ${event.cityName}"
//            tvEventSummary.text = "Summary: ${event.summary}"
//            tvEventCategory.text = "Category: ${event.category}"
//
//            eventLink = event.link
//
//            Glide.with(requireContext())
//                .load(event.mediaCover)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(ivEventImage)
//
//            btnRegister.setOnClickListener {
//                openEventLink()
//            }
//        }
//    }
//
//    private fun formatDate(dateString: String?): String {
//        return try {
//            // Input format sesuai dengan API
//            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//
//            // Output format untuk ditampilkan ke pengguna
//            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
//
//            // Parse dan format ulang
//            val date = inputFormat.parse(dateString ?: "")
//            if (date != null) {
//                outputFormat.format(date)
//            } else {
//                "Invalid date"
//            }
//        } catch (e: Exception) {
//            "Invalid date"
//        }
//    }
//
//    private fun openEventLink() {
//        eventLink?.let { link ->
//            val intent = Intent(Intent.ACTION_VIEW).apply {
//                data = Uri.parse(link)
//            }
//            try {
//                startActivity(intent)
//            } catch (e: ActivityNotFoundException) {
//                Toast.makeText(
//                    requireContext(),
//                    "No browser app found to open the link",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } ?: run {
//            Toast.makeText(requireContext(), "Event link is not available", Toast.LENGTH_SHORT)
//                .show()
//        }
////        fun isFavorite(eventId: Int): LiveData<Boolean> {
////            return repository.isFavorite(eventId)
////        }
////
////        fun toggleFavorite(event: Event) {
////            repository.toggleFavorite(event)
////        }
//    }
//}

package com.okta.subeventappdicoding.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.okta.subeventappdicoding.R
import com.okta.subeventappdicoding.EventViewModel
import com.okta.subeventappdicoding.EventViewModelFactory
import com.okta.subeventappdicoding.Repository.EventRepository
import com.okta.subeventappdicoding.API.RetrofitClient
import com.okta.subeventappdicoding.EventDatabase
import com.okta.subeventappdicoding.databinding.FragmentDetailEventBinding
import com.okta.subeventappdicoding.model.Event
import java.text.SimpleDateFormat
import java.util.Locale

class DetailEventFragment : Fragment() {
    private lateinit var viewModel: EventViewModel
    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = EventDatabase.getDatabase(requireContext())
        val favoriteEventDao = database.favoriteEventDao()

        val repository = EventRepository(RetrofitClient.api, favoriteEventDao)
        val factory = EventViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]

        Log.d("DetailEventFragment", "Arguments: ${arguments?.keySet()}")

        val event = arguments?.getParcelable<Event>("event")

        if (event != null) {
            Log.d("DetailEventFragment", "Received event: ${event.name}")
            displayEventDetails(event)
            viewModel.getEventDetail(event.id)

            // Convert Flow to LiveData and observe
            viewModel.isFavorite(event.id).asLiveData().observe(viewLifecycleOwner) { isFavorite ->
                binding.btnFavorite.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite1
                    else R.drawable.ic_favorite
                )
            }

            // Setup favorite button click
            binding.btnFavorite.setOnClickListener {
                viewModel.toggleFavorite(event)
            }
        } else {
            Log.e("DetailEventFragment", "No event passed to fragment")
            Toast.makeText(requireContext(), "Error loading event details", Toast.LENGTH_SHORT)
                .show()
        }

        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private var eventLink: String? = null

    @SuppressLint("SetTextI18n")
    private fun displayEventDetails(event: Event) {
        with(binding) {
            tvEventName.text = event.name
            tvEventOrganizer.text = "By ${event.ownerName}"
            tvEventDate.text =
                "Event Date: ${formatDate(event.beginTime)} - ${formatDate(event.endTime)}"
            tvEventDescription.text = Html.fromHtml(event.description, Html.FROM_HTML_MODE_COMPACT)
            tvEventQuota.text =
                "Quota: ${event.quota} (Available: ${event.quota - event.registrants})"
            tvEventLocation.text = "Location: ${event.cityName}"
            tvEventSummary.text = "Summary: ${event.summary}"
            tvEventCategory.text = "Category: ${event.category}"

            eventLink = event.link

            Glide.with(requireContext())
                .load(event.mediaCover)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivEventImage)

            btnRegister.setOnClickListener {
                openEventLink()
            }
        }
    }

    private fun formatDate(dateString: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val date = inputFormat.parse(dateString ?: "")
            date?.let { outputFormat.format(it) } ?: "Invalid date"
        } catch (e: Exception) {
            "Invalid date"
        }
    }

    private fun openEventLink() {
        eventLink?.let { link ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(link)
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "No browser app found to open the link",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } ?: run {
            Toast.makeText(requireContext(), "Event link is not available", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
