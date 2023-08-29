package com.amoon.weatherapp.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amoon.weatherapp.MockUtils.mockFavoriteEntity
import com.amoon.weatherapp.data.database.WeatherDao
import com.amoon.weatherapp.data.repositories.DatabaseRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime


@ExperimentalCoroutinesApi
@ExperimentalTime
class DatabaseRepositoryTest {

    private lateinit var repository: DatabaseRepositoryImpl
    private lateinit var dao: WeatherDao

    // This rule swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        dao = mockk()
        repository = DatabaseRepositoryImpl(dao)
    }

    @Test
    fun `getFavorites should return list of favorite entities`() = runBlocking {
        // Given
        val favoriteEntities = listOf(mockFavoriteEntity)

        coEvery { dao.getFavorites() } returns flowOf(favoriteEntities)

        // When
        val result = repository.getFavorites()

        // Then
        result.collect { favorites ->
            assertEquals(favoriteEntities, favorites)
        }
    }
}