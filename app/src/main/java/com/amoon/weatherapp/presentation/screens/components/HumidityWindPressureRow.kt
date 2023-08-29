package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem

/**
 * Composable function for displaying a row of humidity, wind, and pressure information.
 *
 * @param weather UIModelWeatherItem representing weather data for a specific time.
 */
@Composable
fun HumidityWindPressureRow(weather: UIModelWeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HWPItem(icon = R.drawable.humidity, amount = weather.humidity.toString(), unit = "%")
        HWPItem(icon = R.drawable.pressure, amount = weather.pressure.toString(), unit = "psi")
        HWPItem(icon = R.drawable.wind, amount = weather.speed.toString(), unit = " m/s")
    }
}
