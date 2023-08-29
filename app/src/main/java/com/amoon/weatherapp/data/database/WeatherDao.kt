package com.amoon.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

// CRUD operations for the favorite table
@Dao
interface WeatherDao {

    // Retrieves a list of all favorite entities as a Flow
    @Query("SELECT * FROM favorite_tbl")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    // Retrieves a favorite entity based on the city name
    @Query("SELECT * FROM favorite_tbl WHERE city = :city")
    suspend fun getFavoriteByCityName(city: String): FavoriteEntity

    // Inserts a favorite entity into the database with conflict strategy REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    // Updates a favorite entity in the database with conflict strategy REPLACE
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: FavoriteEntity)

    // Deletes a favorite entity from the database
    @Delete
    fun deleteFavorite(favorite: FavoriteEntity)

}