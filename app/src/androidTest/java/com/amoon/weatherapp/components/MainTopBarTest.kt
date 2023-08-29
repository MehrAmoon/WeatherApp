package com.amoon.weatherapp.components

import android.util.Log
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.MockUtils.mockFavoriteEntity
import com.amoon.weatherapp.data.database.entities.UnitEntity
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import com.amoon.weatherapp.domain.usecases.DatabaseUseCase
import com.amoon.weatherapp.presentation.models.UIModelCity
import com.amoon.weatherapp.presentation.models.UIModelCords
import com.amoon.weatherapp.presentation.models.UIModelFeelsLike
import com.amoon.weatherapp.presentation.models.UIModelTemp
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherObject
import com.amoon.weatherapp.presentation.screens.components.MainTopBar
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class MainTopBarTest {

    //UIModelResponse
    val uIModelCords = UIModelCords(52.2, 52.2)
    val uIModelCity = UIModelCity(uIModelCords, "DE", 10, "Berlin", 100, 7200)
    val uIModelFeelsLike = UIModelFeelsLike(0.8, 0.8, 0.8, 0.8)
    val uIModelTemp = UIModelTemp(0.9, 0.9, 0.9, 0.9, 0.9, 0.9)
    val uIModelWeatherObject = UIModelWeatherObject("description", "icon", 1, "main")
    val uIModelWeatherItem = UIModelWeatherItem(
        90,
        1,
        1,
        uIModelFeelsLike,
        0.9,
        1,
        0.9,
        1,
        0.9,
        0.9,
        1,
        1,
        uIModelTemp,
        listOf(uIModelWeatherObject)
    )
    private val uIModelWeather =
        UIModelWeather(uIModelCity, 7, "200", listOf(uIModelWeatherItem), 0.0511463)

    private val unitEntity = UnitEntity("metric", "C")

    @Mock
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var databaseUseCase: DatabaseUseCase

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setup() {
        databaseUseCase = Mockito.mock(databaseUseCase::class.java)
        favoriteViewModel = FavoriteViewModel(databaseUseCase)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testMainTopBar() = runTest {
        // Given
        val weather = uIModelWeather
        val unit = unitEntity
        val weatherItem = weather.list.first()

       // Mock the ViewModel behavior with a MutableStateFlow
        `when`(databaseUseCase.getFavorites()).thenReturn(
            flow {
                emit(
                    listOf(mockFavoriteEntity)
                )
            }
        )

        favoriteViewModel.getFavorites()

        // When
        composeTestRule.setContent {
            MainTopBar(
                weatherItem = weatherItem,
                weather = weather,
                unit = unit,
                favoriteViewModel
            )
            IdlingRegistry.getInstance().resources.forEach {
                Log.d("IDLE Error","resource ${it.name}    idleNow: ${it.isIdleNow}" )
                if (it.name == "Compose-Espresso link") {
                    IdlingRegistry.getInstance().unregister(it)
                }
            }
        }


        // Then
        composeTestRule.onNode(hasTestTag("Favorite Ic")).assertExists()
    }
}