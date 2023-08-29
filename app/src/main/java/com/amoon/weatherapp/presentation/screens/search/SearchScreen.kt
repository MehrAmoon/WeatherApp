package com.amoon.weatherapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amoon.weatherapp.presentation.navigation.AppScreens
import com.amoon.weatherapp.presentation.screens.appBar.WeatherAppBar

/**
 * Composable function to display the search screen.
 *
 * @param navController NavController to handle navigation.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    // Create the search screen using the Scaffold composable
    Scaffold(topBar = {
        // Display the top app bar using the WeatherAppBar composable
        WeatherAppBar(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            elevation = 0.dp,
            onButtonClicked = { navController.popBackStack() }
        )
    }) {
        // Display the content inside a Surface and Column layout
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display the search bar using the SearchBar composable
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    // Navigate to the main screen with the search query
                    navController.navigate(route = "${AppScreens.MainScreen.name}/$it")
                }
            }
        }
    }
}



/**
 * Composable function to display a search bar for city search.
 *
 * @param modifier Modifier for styling.
 * @param onSearch Callback function to be called when the search is initiated.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit = {}) {
    // State to manage the search query
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    // Get the keyboard controller to manage keyboard interactions
    val keyboardController = LocalSoftwareKeyboardController.current
    // Determine whether the search query is valid
    val isValid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }

    Column {
        // Display a text input field for the search query
        CommonTextField(
            valueState = searchQueryState,
            label = "City",
            // Define keyboard actions for the text input field
            onAction = KeyboardActions {
                // Check if the search query is valid before proceeding
                if (!isValid) {
                    return@KeyboardActions
                }
                // Call the onSearch callback with the trimmed search query
                onSearch(searchQueryState.value.trim())
                // Clear the search query and hide the keyboard
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}



/**
 * Composable function to display a common styled text input field.
 *
 * @param valueState MutableState holding the value of the text input.
 * @param label Label text to be displayed in the input field.
 * @param keyboardType KeyboardType for controlling the keyboard behavior.
 * @param imeAction ImeAction to specify the behavior of the keyboard action button.
 * @param onAction KeyboardActions to define the behavior when the keyboard action button is pressed.
 */
@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    // Display an outlined text input field
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}