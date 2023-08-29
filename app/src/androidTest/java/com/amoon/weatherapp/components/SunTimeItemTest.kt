package com.amoon.weatherapp.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.screens.components.SunTimeItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SunTimeItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSunTimeRow() {

        // When
        composeTestRule.setContent {
            SunTimeItem(R.drawable.sunrise,"")
        }

        // Then
        composeTestRule.onNode(hasContentDescription("Sun time icon")).assertExists()
    }
}
