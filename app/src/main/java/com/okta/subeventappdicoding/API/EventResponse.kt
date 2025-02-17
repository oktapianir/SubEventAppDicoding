package com.okta.subeventappdicoding.API

import com.okta.subeventappdicoding.model.Event

data class EventResponse(
    val error: Boolean,
    val message: String,
    val listEvents: List<Event>
)
