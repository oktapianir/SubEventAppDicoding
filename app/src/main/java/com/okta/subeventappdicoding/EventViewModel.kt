package com.okta.subeventappdicoding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.subeventappdicoding.Repository.EventRepository
import com.okta.subeventappdicoding.model.Event
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {
    private val _activeEvents = MutableLiveData<List<Event>>()
    val activeEvents: LiveData<List<Event>> = _activeEvents

    private val _pastEvents = MutableLiveData<List<Event>>()
    val pastEvents: LiveData<List<Event>> = _pastEvents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _eventDetail = MutableLiveData<Event>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    // LiveData untuk loading active events
    private val _activeEventsLoading = MutableLiveData<Boolean>()
    val activeEventsLoading: LiveData<Boolean> = _activeEventsLoading

    // LiveData untuk loading past events
    private val _pastEventsLoading = MutableLiveData<Boolean>()
    val pastEventsLoading: LiveData<Boolean> = _pastEventsLoading

    fun getAllFavorites() = repository.getAllFavorites()

    fun isFavorite(eventId: Int) = repository.isFavorite(eventId)

    fun toggleFavorite(event: Event) {
        viewModelScope.launch {
            repository.toggleFavorite(event)
        }
    }

    fun getEventDetail(eventId: Int) {
        viewModelScope.launch {
            try {
                val event = repository.getEventDetail(eventId)
                _eventDetail.value = event
                Log.d("EventViewModel", "Event detail loaded: ${event?.name}")
            } catch (e: Exception) {
                Log.e("EventViewModel", "Error loading event detail", e)
            }
        }
    }

//    fun loadActiveEvents() {
//        viewModelScope.launch {
//            _loading.value = true
//            try {
//                val response = repository.getActiveEvents()
//                Log.d("EventViewModel", "Received events: ${response.listEvents}")
//                _activeEvents.value = response.listEvents
//            } catch (e: Exception) {
//                errorMessage.value = "Error: ${e.localizedMessage}"
//            } finally {
//                _loading.value = false
//            }
//        }
//    }
//
//    fun loadPastEvents() {
//        viewModelScope.launch {
//            _loading.value = true
//            try {
//                val response = repository.getPastEvents()
//                _pastEvents.value = response.listEvents
//            } catch (_: Exception) {
//                errorMessage.value = "Failed Your connection are bad"
//            } finally {
//                _loading.value = false
//            }
//        }
//    }

    fun loadActiveEvents() {
        viewModelScope.launch {
            try {
                _activeEventsLoading.value = true
                val response = repository.getActiveEvents()
                Log.d("EventViewModel", "Received events: ${response.listEvents}")
                _activeEvents.value = response.listEvents
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                _activeEventsLoading.value = false
            }
        }
    }

    fun loadPastEvents() {
        viewModelScope.launch {
            try {
                _pastEventsLoading.value = true
                val response = repository.getPastEvents()
                _pastEvents.value = response.listEvents
            } catch (e: Exception) {
                errorMessage.value = "Failed: Your connection is bad"
            } finally {
                _pastEventsLoading.value = false
            }
        }
    }
//    fun searchPastEvents(query: String) {
//        viewModelScope.launch {
//            _loading.value = true
//            try {
//                // Use -1 to search across all events (active and non-active)
//                val response = repository.searchEvents(query)
//                _pastEvents.value = response.listEvents
//            } catch (e: Exception) {
//                Log.e("EventViewModel", "Error searching events", e)
//            } finally {
//                _loading.value = false
//            }
//        }
//    }
}
