package com.amoon.weatherapp.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableCloud
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimatableCloudTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAnimatableCloud() {
        // When
        composeTestRule.setContent {
            AnimatableCloud(modifier = Modifier, durationMills = 1500)
        }

        // Then
        // Verify the existence of the cloud image
        composeTestRule.onNodeWithTag("Animatable cloud").assertExists()
    }

}
