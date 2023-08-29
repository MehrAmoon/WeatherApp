package com.amoon.weatherapp.presentation.screens.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.presentation.navigation.AppScreens
import com.amoon.weatherapp.presentation.ui.utils.AppColors
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel

/**
 * Composable function for the weather app's top app bar.
 *
 * @param title The title to display on the app bar.
 * @param icon The navigation icon for the app bar.
 * @param isMainScreen Indicates whether the app bar is on the main screen.
 * @param elevation The elevation of the app bar.
 * @param navController Navigation controller for navigation actions.
 * @param onAddActionClicked Callback for the add action button.
 * @param onButtonClicked Callback for the navigation icon button.
 */
@Composable
fun WeatherAppBar(
    title: String = "City, CT",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 5.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showDropButtons = remember {
        mutableStateOf(false)
    }

    // Display the drop-down menu if enabled
    if (showDropButtons.value) {
        ShowSettingsDropDownMenu(showDropButtons = showDropButtons, navController = navController)
    }

    TopAppBar(
        title = { Text(text = title) },
        elevation = elevation,
        modifier = Modifier.padding(3.dp),
        backgroundColor = Color.Transparent,
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onButtonClicked.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Arrow back icon",
                        tint = Color.Gray
                    )
                }
            }

            // Display the sun icon if on the main screen
            if (isMainScreen) {
                Icon(
                    imageVector = Icons.Rounded.WbSunny,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(start = 15.dp),
                )
            }
        },
        actions = {
            if (isMainScreen) {
                // Display the search icon
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "App Bar Search Icon",
                        tint = Color.Gray
                    )
                }

                // Display the menu icon for settings
                IconButton(onClick = { showDropButtons.value = true }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "App Bar Menu Icon",
                        tint = Color.Gray
                    )
                }
            }
        }
    )
}



/**
 * Composable function to display a drop-down menu for settings.
 *
 * @param showDropButtons State that controls the visibility of the drop-down menu.
 * @param navController Navigation controller used for navigating to different screens.
 */
@Composable
fun ShowSettingsDropDownMenu(
    showDropButtons: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf(
        DropDownMenuItemRepresentation(
            Icons.Default.FavoriteBorder,
            "Favorite",
            AppScreens.FavoriteScreen.name
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDropButtons.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDropButtons.value = false
                }) {
                    Row(modifier = Modifier.clickable {
                        navController.navigate(route = item.route)
                    }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "${item.title} icon",
                            tint = AppColors.LightGray
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.W300,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}


// Representation of a drop-down menu item
data class DropDownMenuItemRepresentation(
    val icon: ImageVector, // Icon associated with the menu item
    val title: String,     // Title text of the menu item
    val route: String      // Route to navigate when the item is selected
)
