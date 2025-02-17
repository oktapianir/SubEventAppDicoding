//package com.okta.subeventappdicoding.fragment
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.okta.subeventappdicoding.EventAdapter
//import com.okta.subeventappdicoding.EventViewModel
//import com.okta.subeventappdicoding.databinding.FragmentFavoriteBinding
//
//class FavoriteFragment : Fragment() {
//    private lateinit var binding: FragmentFavoriteBinding
//    private lateinit var viewModel: EventViewModel
//    private lateinit var favoriteAdapter: EventAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupRecyclerView()
//
//        viewModel.getAllFavorites().observe(viewLifecycleOwner) { favorites ->
//            favoriteAdapter.submitList(favorites)
//            binding.emptyState.isVisible = favorites.isEmpty()
//        }
//    }
//
//    private fun setupRecyclerView() {
//        favoriteAdapter = EventAdapter { event ->
//            findNavController().navigate(
//                FavoriteFragmentDirections.actionFavoriteFragmentToEventDetailFragment(event)
//            )
//        }
//
//        binding.recyclerView.apply {
//            adapter = favoriteAdapter
//            layoutManager = LinearLayoutManager(context)
//        }
//    }
//}


package com.okta.subeventappdicoding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okta.subeventappdicoding.EventAdapter
import com.okta.subeventappdicoding.EventViewModel
import com.okta.subeventappdicoding.EventViewModelFactory
import com.okta.subeventappdicoding.Repository.EventRepository
import com.okta.subeventappdicoding.API.RetrofitClient
import com.okta.subeventappdicoding.EventDatabase
import com.okta.subeventappdicoding.databinding.FragmentFavoriteBinding
import com.okta.subeventappdicoding.model.Event

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventViewModel
    private lateinit var favoriteAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        observeFavorites()
    }

    private fun setupViewModel() {
        val database = EventDatabase.getDatabase(requireContext())
        val favoriteEventDao = database.favoriteEventDao()
        val repository = EventRepository(RetrofitClient.api, favoriteEventDao)
        val factory = EventViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]
    }

    private fun setupRecyclerView() {
        favoriteAdapter = EventAdapter { event ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeFavorites() {
        viewModel.getAllFavorites().asLiveData().observe(viewLifecycleOwner) { favorites ->
            // Convert FavoriteEvent to Event objects
            val events = favorites.map { favorite ->
                Event(
                    id = favorite.eventId,
                    name = favorite.name,
                    summary = favorite.summary,
                    description = favorite.description,
                    imageLogo = favorite.imageLogo,
                    mediaCover = favorite.mediaCover,
                    category = favorite.category,
                    ownerName = favorite.ownerName,
                    cityName = favorite.cityName,
                    quota = favorite.quota,
                    registrants = favorite.registrants,
                    beginTime = favorite.beginTime,
                    endTime = favorite.endTime,
                    link = favorite.link
                )
            }
            favoriteAdapter.submitList(events)
            binding.emptyState.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}