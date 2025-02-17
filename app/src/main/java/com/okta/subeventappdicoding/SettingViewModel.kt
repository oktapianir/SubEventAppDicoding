//package com.okta.subeventappdicoding
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.okta.subeventappdicoding.Repository.EventRepository
//import com.okta.subeventappdicoding.model.Event
//import kotlinx.coroutines.launch
//
//class SettingViewModel(
//    private val repository: EventRepository,
//    private val reminderManager: ReminderManager
//) : ViewModel() {
//
//    private val _reminderStatus = MutableLiveData<ReminderStatus>()
//    val reminderStatus: LiveData<ReminderStatus> = _reminderStatus
//
//    fun getUpcomingEvent() {
//        viewModelScope.launch {
//            try {
//                // Mengambil event terdekat dengan limit=1
////                val response = repository.getEvents(active = -1, limit = 1)
////                if (response.isSuccessful && !response.data.isNullOrEmpty()) {
////                    val upcomingEvent = response.data.first()
////                    scheduleReminder(upcomingEvent)
////                } else {
////                    _reminderStatus.value = ReminderStatus.Error("No upcoming events found")
////                }
//            } catch (e: Exception) {
//                _reminderStatus.value = ReminderStatus.Error(e.message ?: "Unknown error occurred")
//            }
//        }
//    }
//
//    private fun scheduleReminder(event: Event) {
//        viewModelScope.launch {
//            try {
//                reminderManager.scheduleDaily(event)
//                _reminderStatus.value = ReminderStatus.Success(event)
//            } catch (e: Exception) {
//                _reminderStatus.value = ReminderStatus.Error(e.message ?: "Failed to schedule reminder")
//            }
//        }
//    }
//
//    fun cancelReminder() {
//        viewModelScope.launch {
//            reminderManager.cancelDaily()
//            _reminderStatus.value = ReminderStatus.Canceled
//        }
//    }
//}
//
//sealed class ReminderStatus {
//    data class Success(val event: Event) : ReminderStatus()
//    data class Error(val message: String) : ReminderStatus()
//    object Canceled : ReminderStatus()
//}