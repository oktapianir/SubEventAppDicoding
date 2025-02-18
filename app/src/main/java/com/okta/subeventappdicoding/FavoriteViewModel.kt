package com.okta.subeventappdicoding.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.okta.subeventappdicoding.EventDatabase
import com.okta.subeventappdicoding.API.FavoriteEventDao
import com.okta.subeventappdicoding.model.Event
import com.okta.subeventappdicoding.model.FavoriteEvent
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteEventDao: FavoriteEventDao
    private val favoriteEvents: LiveData<List<FavoriteEvent>>

    init {
        val database = EventDatabase.getDatabase(application)
        favoriteEventDao = database.favoriteEventDao()
        favoriteEvents = favoriteEventDao.getAllFavorites().asLiveData()
    }

    fun addToFavorite(event: Event) {
        viewModelScope.launch {
            val favoriteEvent = FavoriteEvent(
                eventId = event.id,
                name = event.name,
                summary = event.summary,
                imageLogo = event.imageLogo,
                beginTime = event.beginTime,
                quota = event.quota,
                registrants = event.registrants,
                ownerName = event.ownerName,
                cityName = event.cityName,
                mediaCover = event.mediaCover,
                endTime = event.endTime,
                category = event.category,
                description = event.description,
                link = event.link
            )
            favoriteEventDao.insert(favoriteEvent)
        }
    }

    fun removeFromFavorite(event: FavoriteEvent) {
        viewModelScope.launch {
            favoriteEventDao.delete(event)
        }
    }

    fun isFavorite(eventId: Int): LiveData<Boolean> {
        return favoriteEventDao.isFavorite(eventId).asLiveData()
    }
    }
