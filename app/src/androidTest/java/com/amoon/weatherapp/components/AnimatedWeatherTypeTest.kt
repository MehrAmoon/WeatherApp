package com.amoon.weatherapp.components

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.presentation.screens.components.AnimatedWeatherType
import com.amoon.weatherapp.presentation.screens.utils.WeatherType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimatedWeatherTypeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAnimatedWeatherType() {
        // Given
        val weatherType = WeatherType.Clouds.name

        // When
        composeTestRule.setContent {
            AnimatedWeatherType(weather = weatherType)
        }

        composeTestRule.onNodeWithTag("Animated Cloud").assertExists()

    }
}
