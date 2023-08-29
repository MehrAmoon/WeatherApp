package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.amoon.weatherapp.BuildConfig
import com.amoon.weatherapp.presentation.screens.utils.DEFAULT_IMAGE
import com.amoon.weatherapp.presentation.screens.utils.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Composable function to display a weather state image using the provided icon.
 *
 * @param icon Icon representing the weather state.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun WeatherStateImage(icon: String) {
    // Construct the image URL using the provided icon
    val imageUrl = "${BuildConfig.IMG_BASE_URL}${icon}.png"

    // Load the image using the loadPicture function
    val image = imageUrl.let {
        loadPicture(
            url = it,
            defaultImage = DEFAULT_IMAGE
        ).value
    }

    // Display the image if it's loaded successfully
    image?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = "Weather representation icon",
            modifier = Modifier
                .size(50.dp),
            contentScale = ContentScale.Crop,
        )
    }
}