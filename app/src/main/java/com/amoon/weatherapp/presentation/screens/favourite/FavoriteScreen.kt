package com.amoon.weatherapp.presentation.screens.favourite

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.presentation.navigation.AppScreens
import com.amoon.weatherapp.presentation.screens.appBar.WeatherAppBar
import com.amoon.weatherapp.presentation.ui.utils.AppColors
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel

/**
 * Composable function to display the favorite cities screen.
 *
 * @param navController NavController used for navigation.
 * @param viewModel ViewModel associated with the favorite cities screen.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel) {
    // Scaffold to provide the layout structure
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorite Cities",
            navController = navController,
            isMainScreen = false,
            icon = Icons.Default.ArrowBack,
            onButtonClicked = { navController.popBackStack() }
        )
    }) {
        // Get the list of favorite cities using the ViewModel
        viewModel.getFavorites()

        // Display the list of favorite cities using FavoriteList composable
        FavoriteList(viewModel = viewModel, navController = navController)
    }
}


/**
 * Composable function to display the list of favorite cities.
 *
 * @param viewModel ViewModel associated with the favorite cities screen.
 * @param navController NavController used for navigation.
 */
@Composable
fun FavoriteList(viewModel: FavoriteViewModel, navController: NavController) {
    // Collect the list of favorite cities from the ViewModel
    val favorites = viewModel.favorites.collectAsState().value

    // Display the list of favorite cities using a LazyColumn
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(content = {
            items(items = favorites) { favorite ->
                // Display each favorite city using the CityRow composable
                CityRow(favorite = favorite, viewModel, navController)
            }
        })
    }
}


/**
 * Composable function to display a row representing a favorite city.
 *
 * @param favorite The FavoriteEntity representing the favorite city.
 * @param viewModel ViewModel associated with the favorite cities screen.
 * @param navController NavController used for navigation.
 */
@Composable
fun CityRow(favorite: FavoriteEntity, viewModel: FavoriteViewModel, navController: NavController) {
    // Display a row representing a favorite city using a Surface
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                navController.navigate("${AppScreens.MainScreen.name}/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(5.dp)),
        color = AppColors.Teal
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Display the city name
            Text(text = favorite.city)

            // Display the country name in a Surface
            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = AppColors.LightGray
            ) {
                Text(text = favorite.country, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }

            // Display a delete icon button
            IconButton(onClick = { viewModel.deleteFavorite(favorite) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete favorite icon",
                    tint = Color.Red.copy(alpha = .3f)
                )
            }
        }
    }
}