package com.okta.subeventappdicoding

import android.app.Application
import com.okta.subeventappdicoding.API.RetrofitClient
import com.okta.subeventappdicoding.Repository.EventRepository

class EventApplication : Application() {
    val database: EventDatabase by lazy { EventDatabase.getDatabase(this) }
    val repository by lazy {
        EventRepository(
            RetrofitClient.api,
            database.favoriteEventDao()
        )
    }
}