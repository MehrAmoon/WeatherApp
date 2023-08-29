package com.amoon.weatherapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.MockUtils.uIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.screens.components.HumidityWindPressureRow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HumidityWindPressureRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHumidityWindPressureRow() {
        // Given
        val mockWeather = uIModelWeatherItem

        // When
        composeTestRule.setContent {
            HumidityWindPressureRow(weather = mockWeather)
        }

        // Then
        composeTestRule.onNodeWithText("1%").assertExists()
        composeTestRule.onNodeWithText("1psi").assertExists()
        composeTestRule.onNodeWithText("0.9 m/s").assertExists()
    }
}
