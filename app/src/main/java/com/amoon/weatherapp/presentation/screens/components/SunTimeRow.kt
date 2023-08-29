package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.screens.utils.formatDateTime
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import com.amoon.weatherapp.presentation.viewmodel.MainViewModel


/**
 * Composable function to display a row containing sunrise and sunset times.
 *
 * @param weather UIModelWeatherItem containing weather data.
 */
@Composable
fun SunTimeRow(
    weather: UIModelWeatherItem,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Display sunrise time
        SunTimeItem(icon = R.drawable.sunrise, time = mainViewModel.convertToFormatDateTime(weather.sunrise))

        // Display sunset time
        SunTimeItem(icon = R.drawable.sunset, time = mainViewModel.convertToFormatDateTime(weather.sunset))
    }
}