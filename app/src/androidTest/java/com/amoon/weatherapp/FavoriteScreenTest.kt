package com.amoon.weatherapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.amoon.weatherapp.MockUtils.mockNavController
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import com.amoon.weatherapp.domain.usecases.DatabaseUseCase
import com.amoon.weatherapp.presentation.screens.favourite.CityRow
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class FavoriteScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var databaseUseCase: DatabaseUseCase

    @Mock
    private lateinit var repository: DatabaseRepository

    @Mock
    private lateinit var favoriteViewModel: FavoriteViewModel


    @Before
    fun setUp() {
        repository = mock(DatabaseRepository::class.java)
        databaseUseCase = DatabaseUseCase(repository)

        // Create the FavoriteViewModel with the repository
        favoriteViewModel = FavoriteViewModel(databaseUseCase)
    }


    @Test
    fun testCityRowNavigation() {

        // Set the content of the CityRow
        composeTestRule.setContent {
            CityRow(
                favorite = FavoriteEntity("Berlin", "DE"),
                viewModel = favoriteViewModel,
                navController = mockNavController
            )
        }

        composeTestRule.onNodeWithContentDescription("Delete favorite icon").performClick()

    }
}
