package com.amoon.weatherapp.presentation.screens.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.data.database.entities.UnitEntity
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.ui.theme.Purple40
import com.amoon.weatherapp.presentation.ui.theme.Purple80
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel

/**
 * Composable function to display the top bar section of the main screen, showing weather details.
 *
 * @param weatherItem UIModel representing the weather details for a specific day.
 * @param weather UIModel representing the overall weather information.
 * @param unit UnitEntity representing the unit of temperature measurement.
 * @param favoriteViewModel ViewModel for managing favorite cities.
 */
@Composable
fun MainTopBar(
    weatherItem: UIModelWeatherItem,
    weather: UIModelWeather,
    unit: UnitEntity,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    // Fetch favorite cities from the view model
    favoriteViewModel.getFavorites()

    // Create a FavoriteEntity based on the current weather city
    val favorite = FavoriteEntity(city = weather.city.name, country = weather.city.country)

    // Check if the current weather city is already in the list of favorites
    val isAlreadyFavorite = favoriteViewModel.favorites
        .collectAsState()
        .value
        .contains(favorite)

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .size(200.dp),
        color = Purple80,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row containing favorite icon and date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = if (isAlreadyFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .testTag("Favorite Icon")
                        .padding(start = 10.dp, end = 120.dp)
                        .scale(0.9f)
                        .clickable {
                            if (!isAlreadyFavorite) {
                                favoriteViewModel
                                    .addFavorite(favorite)
                                    .run {
                                        Toast
                                            .makeText(
                                                context,
                                                "Added to favorite list",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            }
                        },
                    tint = if (isAlreadyFavorite) Color.Red else Color.Black
                )

                Text(
                    text = favoriteViewModel.convertToFormatDate(weather.list.first().dt),
                    style = MaterialTheme.typography.body2,
                    color = Purple40,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(6.dp)
                        .weight(3f)
                )
            }

            // Animated weather icon
            AnimatedWeatherType(weatherItem.weather.first().main)

            // Temperature display
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text =
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    ) {
                        append("${favoriteViewModel.convertToFormatDecimal(weatherItem.temp.day)}º")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp
                        )
                    ) {
                        append(unit.symbol)
                    }
                }
            )

            // Weather description
            Text(
                text = weatherItem.weather.first().main.toString(),
                fontStyle = FontStyle.Italic,
                color = Purple40
            )
        }
    }
}