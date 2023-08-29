package com.amoon.weatherapp.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.presentation.screens.components.AnimationButton
import com.amoon.weatherapp.presentation.screens.components.CustomSnackBar
import com.amoon.weatherapp.presentation.screens.components.SnackBar
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSnackBar() {
        // Given
        var retryClicked = false
        val onClickRetry: () -> Unit = { retryClicked = true }

        // When
        composeTestRule.setContent {
            SnackBar(
                message = "An error occurred",
                onClickRetry = onClickRetry
            )
        }

        // Then
        // Assert message text
        composeTestRule.onNodeWithText("An error occurred").assertExists()

        // Click the Retry button
        composeTestRule.onNodeWithText("Retry").performClick()

        // Verify that the onClickRetry function is called
        assert(retryClicked)
    }

    @Test
    fun testCustomSnackBar() {
        // Given
        var retryClicked = false
        val onClickRetry: () -> Unit = { retryClicked = true }

        // When
        composeTestRule.setContent {
            CustomSnackBar(
                message = "An error occurred",
                containerColor = Color.Gray,
                onClickRetry = onClickRetry
            )
        }

        // Then
        // Assert message text
        composeTestRule.onNodeWithText("An error occurred").assertExists()

        // Click the Retry button
        composeTestRule.onNodeWithText("Retry").performClick()

        // Verify that the onClickRetry function is called
        assert(retryClicked)
    }


    @Test
    fun testAnimationButton() {
        // Given
        var clickCount = 0
        val onClickRetry: () -> Unit = { clickCount++ }

        // When
        composeTestRule.setContent {
            AnimationButton(text = "Retry", onClickRetry = onClickRetry)
        }

        // Then
        // Assert button text
        composeTestRule.onNodeWithText("Retry").assertExists()

        // Click the button
        composeTestRule.onNodeWithText("Retry").performClick()

        // Verify that the onClickRetry function is called
        assert(clickCount == 1)
    }
}
