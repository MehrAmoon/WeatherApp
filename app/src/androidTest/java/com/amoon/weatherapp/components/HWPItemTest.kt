package com.amoon.weatherapp.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.presentation.screens.components.HWPItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.amoon.weatherapp.R

@RunWith(AndroidJUnit4::class)
class HWPItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHWPItem() {
        // Given
        val iconId = R.drawable.humidity
        val amount = "10"
        val unit = "kg"

        // When
        composeTestRule.setContent {
            HWPItem(icon = iconId, amount = amount, unit = unit)
        }

        // Then
        composeTestRule.onNodeWithContentDescription("HWP icon").assertExists()
        composeTestRule.onNodeWithText("$amount$unit").assertExists()
    }
}
