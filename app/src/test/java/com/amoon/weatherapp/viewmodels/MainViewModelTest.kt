package com.amoon.weatherapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amoon.weatherapp.MockUtils.uIModelWeather
import com.amoon.weatherapp.data.utils.ScreenState
import com.amoon.weatherapp.domain.usecases.WeatherUseCase
import com.amoon.weatherapp.presentation.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class MainViewModelTest {

    // This rule swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    // Use the UnconfinedTestDispatcher for testing Coroutines
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainViewModel
    private lateinit var weatherUseCase: WeatherUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        weatherUseCase = mockk()
        viewModel = MainViewModel(weatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getWeatherData should update weatherReposState with success`() = runBlocking {
        // Given
        val city = "Berlin"
        val expectedUIModel = uIModelWeather
        val weatherUseCase = mockk<WeatherUseCase>()

        coEvery { weatherUseCase.weather(city) } returns expectedUIModel

        val viewModel = MainViewModel(weatherUseCase)

        // When
        viewModel.getWeatherData(city)


        // Then
        val currentState = viewModel.weatherReposState.value
        // Assert and verify the current state as needed
        assertEquals(ScreenState.Success(expectedUIModel), currentState)
    }


    @Test
    fun `getWeatherData should update weatherReposState with fail`() = runBlocking {
        // Given
        val city = "InvalidCity"
        val expectedException = Exception("API call failed")
        val weatherUseCase = mockk<WeatherUseCase>()

        coEvery { weatherUseCase.weather(city) } throws expectedException

        val viewModel = MainViewModel(weatherUseCase)

        // When
        viewModel.getWeatherData(city)

        // Then
        val currentState = viewModel.weatherReposState.value
        // Assert and verify the current state as needed
        assertEquals(ScreenState.Error(expectedException), currentState)
    }


    @Test
    fun `getWeatherData should update weatherReposState with loading`() = runBlocking {
        // Given
        val city = "Berlin"
        val weatherUseCase = mockk<WeatherUseCase>()

        coEvery { weatherUseCase.weather(city) } coAnswers {
            // Simulate a delay to demonstrate loading state
            kotlinx.coroutines.delay(100)
            uIModelWeather
        }

        val viewModel = MainViewModel(weatherUseCase)

        // When
        viewModel.getWeatherData(city)

        // Then
        val currentState = viewModel.weatherReposState.value
        // Assert and verify the current state as needed
        assertEquals(ScreenState.Loading, currentState)
    }
}