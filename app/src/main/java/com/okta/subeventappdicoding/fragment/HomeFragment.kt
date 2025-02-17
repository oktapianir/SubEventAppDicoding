package com.okta.subeventappdicoding.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okta.subeventappdicoding.EventAdapter
import com.okta.subeventappdicoding.EventViewModel
import com.okta.subeventappdicoding.EventViewModelFactory
import com.okta.subeventappdicoding.EventApplication
import com.okta.subeventappdicoding.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
//    private lateinit var viewModel: EventViewModel
    private lateinit var activeEventAdapter: EventAdapter
    private lateinit var pastEventAdapter: EventAdapter
    private val viewModel: EventViewModel by viewModels {
        EventViewModelFactory((requireActivity().application as EventApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupViewModel()
        setupAdapters()
        setupRecyclerViews()
        setupObservers()
        loadData()
    }

//    private fun setupViewModel() {
//        val repository = EventRepository(RetrofitClient.api)
//        val factory = EventViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]
//    }

    private fun setupAdapters() {
        activeEventAdapter = EventAdapter { event ->
            Log.d("HomeFragment", "Active Event clicked: ${event.name}")
            val action = HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }

        pastEventAdapter = EventAdapter { event ->
            Log.d("HomeFragment", "Past Event clicked: ${event.name}")
            val action = HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerViews() {
        binding.rvActiveEvents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = activeEventAdapter
        }

        binding.rvPastEvents.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pastEventAdapter
        }
    }

    private fun setupObservers() {
        viewModel.activeEvents.observe(viewLifecycleOwner) { events ->
            activeEventAdapter.submitList(events)
        }

        viewModel.pastEvents.observe(viewLifecycleOwner) { events ->
            pastEventAdapter.submitList(events)
        }

        // Loading observers
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarActiveEvents.isVisible = isLoading
            // Sembunyikan konten saat loading
            binding.progressBarPastEvents.isVisible = !isLoading
        }

        viewModel.activeEventsLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarActiveEvents.isVisible = isLoading
            binding.rvActiveEvents.isVisible = !isLoading
        }

        viewModel.pastEventsLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarPastEvents.isVisible = isLoading
            binding.rvPastEvents.isVisible = !isLoading
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                showErrorDialog(it)
            }
        }
    }

    private fun loadData() {
        viewModel.loadActiveEvents()
        viewModel.loadPastEvents()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}