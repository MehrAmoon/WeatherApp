package com.amoon.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amoon.weatherapp.presentation.screens.favourite.FavoriteScreen
import com.amoon.weatherapp.presentation.screens.main.MainScreen
import com.amoon.weatherapp.presentation.screens.search.SearchScreen
import com.amoon.weatherapp.presentation.screens.splash.SplashScreen
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import com.amoon.weatherapp.presentation.viewmodel.MainViewModel

@Composable
fun AppNavigation() {

    // Create a navigation controller
    val navController = rememberNavController()

    // Define navigation routes
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name) {
        // SplashScreen route
        composable(route = AppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        // MainScreen route with dynamic city argument
        val mainScreenRoute = AppScreens.MainScreen.name
        composable(
            route = "$mainScreenRoute/{city}",
            arguments = listOf(
                navArgument(name = "city", builder = {
                    type = NavType.StringType
                })
            )
        ) { navBackStackEntry ->
            // Retrieve the city argument from the navigation entry
            val city = navBackStackEntry.arguments?.getString("city")!!

            // Obtain MainViewModel using Hilt
            val mainViewModel = hiltViewModel<MainViewModel>()

            // Request weather data for the city from the MainViewModel
            mainViewModel.getWeatherData(city)

            // Display the MainScreen with the retrieved data
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                city = city
            )
        }

        // SearchScreen route
        composable(route = AppScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        // FavoriteScreen route
        composable(route = AppScreens.FavoriteScreen.name) {
            // Obtain FavoriteViewModel using Hilt
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

            // Display the FavoriteScreen with the retrieved FavoriteViewModel
            FavoriteScreen(navController = navController, viewModel = favoriteViewModel)
        }
    }
}
