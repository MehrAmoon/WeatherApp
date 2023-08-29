package com.amoon.weatherapp.presentation.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.amoon.weatherapp.data.database.entities.UnitEntity
import com.amoon.weatherapp.data.utils.ScreenState
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.navigation.AppScreens
import com.amoon.weatherapp.presentation.screens.appBar.WeatherAppBar
import com.amoon.weatherapp.presentation.screens.components.ForecastList
import com.amoon.weatherapp.presentation.screens.components.HumidityWindPressureRow
import com.amoon.weatherapp.presentation.screens.components.LoadingScreen
import com.amoon.weatherapp.presentation.screens.components.MainTopBar
import com.amoon.weatherapp.presentation.screens.components.SnackBar
import com.amoon.weatherapp.presentation.screens.components.SunTimeRow
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import com.amoon.weatherapp.presentation.viewmodel.MainViewModel

/**
 * Composable function to display the main screen of the weather app.
 *
 * @param navController NavHostController used for navigation.
 * @param mainViewModel ViewModel associated with the main screen.
 * @param city The city for which weather information is being displayed.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String? = "Berlin"
) {

    val TAG = "ManScreen"

    // Collect the weather repository state using the mainViewModel
    val screenState = mainViewModel.weatherReposState.collectAsState()

    // Handle different screen states using when expression
    when (screenState.value) {
        is ScreenState.Loading -> {
            // Show a loading screen if data is being loaded
            LoadingScreen()
        }

        is ScreenState.Success -> {
            // Display weather information if data is successfully loaded
            val data = (screenState.value as ScreenState.Success<UIModelWeather>).data

            val unitObject = UnitEntity()
            MainScaffold(weather = data, navController = navController, unit = unitObject)
        }

        is ScreenState.Error -> {
            // Show an error message and retry option if there's an error
            val errorMessage = (screenState.value as ScreenState.Error).throwable.localizedMessage

            if (errorMessage != null) {
                SnackBar(
                    message = errorMessage,
                    onClickRetry = {
                        mainViewModel.getWeatherData("Berlin")
                    }
                )
            }
        }

        else -> {
            Log.d(TAG, screenState.value.toString())
        }
    }
}


/**
 * Composable function to display the main scaffold of the app.
 *
 * @param weather UIModelWeather object containing weather information.
 * @param navController NavHostController used for navigation.
 * @param unit UnitEntity object containing unit information.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    weather: UIModelWeather,
    navController: NavController,
    unit: UnitEntity
) {
    // Create the main scaffold using the Scaffold composable
    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name}, ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(route = AppScreens.SearchScreen.name)
            }
        )
    }) {
        // Display the main content using the MainContent composable
        MainContent(weather = weather, unit = unit)
    }
}



/**
 * Composable function to display the main content of the app.
 *
 * @param weather UIModelWeather object containing weather information.
 * @param unit UnitEntity object containing unit information.
 */
@Composable
fun MainContent(
    weather: UIModelWeather,
    unit: UnitEntity
) {
    val weatherItem = weather.list.first()

    // Create the main content using a Column layout
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the main top bar using the MainTopBar composable
        MainTopBar(weatherItem, weather, unit)

        // Display the humidity, wind, and pressure information using the HumidityWindPressureRow composable
        HumidityWindPressureRow(weather = weatherItem)

        // Add a divider
        Divider()

        // Display the sunrise and sunset times using the SunTimeRow composable
        SunTimeRow(weather = weatherItem)

        // Display the forecast list using the ForecastList composable
        ForecastList(weather.list)
    }
}