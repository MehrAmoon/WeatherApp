package com.amoon.weatherapp.data.repositories

import com.amoon.weatherapp.data.database.WeatherDao
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// DatabaseRepositoryImpl class implementing the DatabaseRepository interface
class DatabaseRepositoryImpl @Inject constructor(
    private val dao: WeatherDao // DAO (Data Access Object) for database operations
) : DatabaseRepository {

    // Retrieve a Flow of all favorite entities from the database
    override fun getFavorites(): Flow<List<FavoriteEntity>> = dao.getFavorites()

    // Add a favorite entity to the database
    override suspend fun addFavorite(favorite: FavoriteEntity) = dao.insertFavorite(favorite)

    // Delete a favorite entity from the database
    override fun deleteFavorite(favorite: FavoriteEntity) = dao.deleteFavorite(favorite)
}
