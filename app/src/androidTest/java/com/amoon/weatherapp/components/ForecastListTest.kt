package com.amoon.weatherapp.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.MockUtils.uIModelWeatherItem
import com.amoon.weatherapp.presentation.screens.components.ForecastList
import com.amoon.weatherapp.presentation.screens.utils.formatDate
import com.amoon.weatherapp.presentation.screens.utils.formatDateTime
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testForecastList_weekLabelExists() {
        // Given
        val mockItems = listOf(uIModelWeatherItem)

        // When
        composeTestRule.setContent {
            ForecastList(items = mockItems)
        }

        // Then
        composeTestRule.onNodeWithText("THIS WEEK FORECAST").assertExists()
    }

    @Test
    fun testForecastList() {
        // Given
        val mockItems = listOf(uIModelWeatherItem)

        // When
        composeTestRule.setContent {
            ForecastList(items = mockItems)
        }

        // Then
        val day = formatDate(mockItems[0].dt).subSequence(0, 3).toString()
        composeTestRule.onNodeWithText(day).assertExists()

        composeTestRule.onNodeWithText("sky is clear").assertExists()

    }

    @Test
    fun testForecastList_iconExists() {
        // Given
        val mockItems = listOf(uIModelWeatherItem)

        // When
        composeTestRule.setContent {
            ForecastList(items = mockItems)
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Icon").assertExists()
    }

}
