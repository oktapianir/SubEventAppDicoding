package com.okta.subeventappdicoding.fragment

import android.os.Bundle
import android.view.View

class PastEventsFragment : BaseEventFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pastEvents.observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
        }
        viewModel.loadPastEvents()
    }

    override fun setupSpecificLoadingObserver() {
        viewModel.pastEventsLoading.observe(viewLifecycleOwner) { isLoading ->
            updateLoadingState(isLoading)
        }
    }
}

