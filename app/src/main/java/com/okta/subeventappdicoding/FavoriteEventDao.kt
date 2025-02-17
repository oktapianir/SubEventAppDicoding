package com.okta.subeventappdicoding

import androidx.room.*
import com.okta.subeventappdicoding.model.FavoriteEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteEventDao {
    @Query("SELECT * FROM favorite_events")
    fun getAllFavorites(): Flow<List<FavoriteEvent>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_events WHERE eventId = :eventId)")
    fun isFavorite(eventId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: FavoriteEvent)

    @Delete
    suspend fun delete(event: FavoriteEvent)

    @Query("DELETE FROM favorite_events WHERE eventId = :eventId")
    suspend fun deleteById(eventId: Int)
}
