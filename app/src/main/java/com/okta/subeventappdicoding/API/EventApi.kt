package com.okta.subeventappdicoding.API

import com.okta.subeventappdicoding.model.Event
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventApi {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int,
        @Query("q") query: String? = null
    ): EventResponse

    @GET("events/{id}")
    suspend fun getEventDetail(@Path("id") eventId: Int): Event
}

