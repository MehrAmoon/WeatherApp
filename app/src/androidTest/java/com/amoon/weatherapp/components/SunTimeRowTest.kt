package com.amoon.weatherapp.components

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.MockUtils.uIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelCity
import com.amoon.weatherapp.presentation.models.UIModelCords
import com.amoon.weatherapp.presentation.models.UIModelFeelsLike
import com.amoon.weatherapp.presentation.models.UIModelTemp
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherObject
import com.amoon.weatherapp.presentation.screens.components.SunTimeRow
import com.amoon.weatherapp.presentation.screens.utils.formatDateTime
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SunTimeRowTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSunTimeRow() {
        // Given
        val mockWeather = uIModelWeatherItem

        // When
        composeTestRule.setContent {
            SunTimeRow(weather = mockWeather)
        }

        // Wait for Composable to reach an idle state
        composeTestRule.waitForIdle()

        // Then
        val sunriseText = formatDateTime(mockWeather.sunrise)
        val sunsetText = formatDateTime(mockWeather.sunset)

        composeTestRule.onNodeWithText(sunriseText).assertExists()
        composeTestRule.onNodeWithText(sunsetText).assertExists()
    }
}
