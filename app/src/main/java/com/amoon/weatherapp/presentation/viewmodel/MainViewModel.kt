package com.amoon.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.weatherapp.data.utils.ScreenState
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.domain.usecases.WeatherUseCase
import com.amoon.weatherapp.presentation.screens.utils.formatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
    ) : ViewModel() {

    val weatherReposState: MutableStateFlow<ScreenState<UIModelWeather>?> = MutableStateFlow(null)


    fun getWeatherData(city: String) {
        viewModelScope.launch {
            try {
                weatherReposState.value = ScreenState.Loading
                val result = weatherUseCase.weather(city)
                weatherReposState.value = ScreenState.Success(result)
            } catch (e: Exception) {
                weatherReposState.value = ScreenState.Error(e)
            }
        }
    }

    fun convertToFormatDateTime(timestamp: Int) :  String {
        return formatDate(timestamp)
    }
}