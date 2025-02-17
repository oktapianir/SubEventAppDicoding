package com.okta.subeventappdicoding.fragment

import android.os.Bundle
import android.view.View

class ActiveEventsFragment : BaseEventFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activeEvents.observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
        }
        viewModel.loadActiveEvents()
    }

    override fun setupSpecificLoadingObserver() {
        viewModel.activeEventsLoading.observe(viewLifecycleOwner) { isLoading ->
            updateLoadingState(isLoading)
        }
    }

}