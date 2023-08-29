package com.amoon.weatherapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amoon.weatherapp.MockUtils.mockFavoriteEntity
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.usecases.DatabaseUseCase
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteViewModelTest {

    // This rule swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    // Use the UnconfinedTestDispatcher for testing Coroutines
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var usecase: DatabaseUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        usecase = mockk()
        viewModel = FavoriteViewModel(usecase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `favorites should emit initial empty list`() = runBlocking {
        // Given
        val initialFavorites = emptyList<FavoriteEntity>()
        coEvery { usecase.getFavorites() } returns flowOf(initialFavorites)

        // When
        val favorites = viewModel.favorites.value

        // Then
        assertEquals(initialFavorites, favorites)
    }

    @Test
    fun `addFavorite should call repository's addFavorite`() = runBlocking {
        // Given
        val favorite = mockFavoriteEntity

        // When
        viewModel.addFavorite(favorite)

        // Then
        coVerify { usecase.addFavorite(favorite) }
    }

    @Test
    fun `deleteFavorite should call repository's deleteFavorite`() = runBlocking {
        // Given
        val favorite = mockFavoriteEntity

        // When
        viewModel.deleteFavorite(favorite)

        // Then
        coVerify { usecase.deleteFavorite(favorite) }
    }
}