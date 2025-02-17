package com.okta.subeventappdicoding.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okta.subeventappdicoding.EventAdapter
import com.okta.subeventappdicoding.EventViewModel
import com.okta.subeventappdicoding.EventViewModelFactory
import com.okta.subeventappdicoding.R
import com.okta.subeventappdicoding.Repository.EventRepository
import com.okta.subeventappdicoding.API.RetrofitClient
import com.okta.subeventappdicoding.EventDatabase
import com.okta.subeventappdicoding.databinding.FragmentBaseEventBinding

abstract class BaseEventFragment : Fragment() {
    private lateinit var binding: FragmentBaseEventBinding
    protected lateinit var viewModel: EventViewModel

    protected val eventAdapter = EventAdapter { event ->
        Log.d("BaseEventFragment", "Event clicked: ${event.name}")
        val bundle = Bundle().apply {
            putParcelable("event", event)
        }
        try {
            val navAction = when (this) {
                is ActiveEventsFragment -> R.id.action_activeEventsFragment_to_eventDetailFragment
                is PastEventsFragment -> R.id.action_pastEventsFragment_to_eventDetailFragment
                else -> return@EventAdapter
            }
            findNavController().navigate(navAction, bundle)
        } catch (e: Exception) {
            Log.e("BaseEventFragment", "Navigation error", e)
            e.printStackTrace()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseEventBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val database = EventDatabase.getDatabase(requireContext())
        val FavoriteEventDao = database.favoriteEventDao()

        val repository = EventRepository(RetrofitClient.api, FavoriteEventDao)
        val factory = EventViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]

        binding.recyclerView.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
        setupLoadingObserver()
    }

    private fun setupLoadingObserver() {
        // Implementation will be different for active and past events
        setupSpecificLoadingObserver()
    }
    protected abstract fun setupSpecificLoadingObserver()

    // Helper function to update loading state
    protected fun updateLoadingState(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerView.isVisible = !isLoading
    }
}