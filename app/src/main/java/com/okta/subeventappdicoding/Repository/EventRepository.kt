    package com.okta.subeventappdicoding.Repository

    import android.util.Log
    import com.okta.subeventappdicoding.API.EventApi
    import com.okta.subeventappdicoding.API.EventResponse
    import com.okta.subeventappdicoding.FavoriteEventDao
    import com.okta.subeventappdicoding.model.Event
    import com.okta.subeventappdicoding.model.FavoriteEvent
    import kotlinx.coroutines.flow.first

    class EventRepository(private val api: EventApi,    private val FavoriteEventDao: FavoriteEventDao) {
        suspend fun getEventDetail(eventId: Int): Event? {
            return try {
                api.getEventDetail(eventId)
            } catch (e: Exception) {
                Log.e("EventRepository", "Error fetching event detail: ${e.localizedMessage}")
                null
            }
        }

        suspend fun getActiveEvents(): EventResponse {
            try {
                val response = api.getEvents(active = 1)
                Log.d("EventRepository", "Active Events Response: ${response.listEvents}")
                return response
            } catch (e: Exception) {
                Log.e("EventRepository", "Error fetching active events", e)
                throw e
            }
        }

        suspend fun getPastEvents(): EventResponse {
            try {
                val response = api.getEvents(active = 0)
                Log.d("EventRepository", "Past Events Response: ${response.listEvents}")
                return response
            } catch (e: Exception) {
                Log.e("EventRepository", "Error fetching past events", e)
                throw e
            }
        }

        fun getAllFavorites() = FavoriteEventDao.getAllFavorites()

        fun isFavorite(eventId: Int) = FavoriteEventDao.isFavorite(eventId)

        suspend fun toggleFavorite(event: Event) {
            val isFavorite = FavoriteEventDao.isFavorite(event.id).first()

            if (isFavorite) {
                FavoriteEventDao.deleteById(event.id)
            } else {
                val favoriteEvent = FavoriteEvent(
                    eventId = event.id,
                    name = event.name,
                    summary = event.summary,
                    imageLogo = event.imageLogo,
                    mediaCover = event.mediaCover,
                    beginTime = event.beginTime,
                    endTime = event.endTime,
                    quota = event.quota,
                    registrants = event.registrants,
                    ownerName = event.ownerName,
                    cityName = event.cityName,
                    category = event.category,
                    description = event.description,
                    link = event.link
                )
                FavoriteEventDao.insert(favoriteEvent)
            }
        }
    }


        // **Tambahan fitur favorit**
//        fun getAllFavorites(): LiveData<List<FavoriteEvent>> {
//            return FavoriteEventDao.getAllFavorites()
//        }
//
//        fun isFavorite(eventId: Int): LiveData<Boolean> {
//            return FavoriteEventDao.isFavorite(eventId)
//        }
//
//        suspend fun toggleFavorite(event: Event) {
//            withContext(Dispatchers.IO) {
//                val isFav = isFavorite(event.id).value ?: false
//                val favoriteEvent = FavoriteEvent(
//                    eventId = event.id,
//                    name = event.name,
//                    ownerName = event.ownerName,
//                    beginTime = event.beginTime,
//                    cityName = event.cityName,
//                    imageLogo = event.imageLogo
//                )
//
//                if (isFav) {
//                    FavoriteEventDao.delete(favoriteEvent)
//                    Log.d("EventRepository", "Event ${event.name} removed from favorites")
//                } else {
//                    FavoriteEventDao.insert(favoriteEvent)
//                    Log.d("EventRepository", "Event ${event.name} added to favorites")
//                }
//            }
//        }

        //    suspend fun searchEvents(query: String): EventResponse {
    //        return try {
    //            val response = api.getEvents(active = -1, query = query)
    //            Log.d("EventRepository", "Search Events Response: ${response.listEvents}")
    //            response
    //        } catch (e: Exception) {
    //            Log.e("EventRepository", "Error searching events", e)
    //            throw e
    //        }
    //    }
