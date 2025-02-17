package com.okta.subeventappdicoding.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_events")
data class FavoriteEvent(
    @PrimaryKey
    val eventId: Int,
    val name: String,
    val summary: String,
    val imageLogo: String,
    val mediaCover: String,
    val beginTime: String,
    val endTime: String,
    val quota: Int,
    val registrants: Int,
    val ownerName: String,
    val cityName: String,
    val category: String,
    val description: String,
    val link: String
)