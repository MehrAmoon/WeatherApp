package com.amoon.weatherapp.domain.usecases

import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Represents a use case for interacting with favorite cities data in the database
open class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    // Retrieves a Flow of favorite cities from the database
    fun getFavorites(): Flow<List<FavoriteEntity>> {
        return databaseRepository.getFavorites()
    }

    // Adds a new favorite city to the database
    suspend fun addFavorite(favorite: FavoriteEntity) {
        return databaseRepository.addFavorite(favorite)
    }

    // Deletes a favorite city from the database
    fun deleteFavorite(favorite: FavoriteEntity) {
        return databaseRepository.deleteFavorite(favorite)
    }

}