package com.amoon.weatherapp.domain.repositories

import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

// Represents a repository for interacting with local database
interface DatabaseRepository {

    // Retrieves a flow of favorite entities from the database
    fun getFavorites(): Flow<List<FavoriteEntity>>

    // Adds a favorite entity to the database
    suspend fun addFavorite(favorite: FavoriteEntity)

    // Deletes a favorite entity from the database
    fun deleteFavorite(favorite: FavoriteEntity)

}