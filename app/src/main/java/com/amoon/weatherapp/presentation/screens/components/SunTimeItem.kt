package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


/**
 * Composable function to display a sun time item with an icon and time.
 *
 * @param icon Resource ID of the icon to be displayed.
 * @param time String representing the sun time.
 */
@Composable
fun SunTimeItem(icon: Int, time: String) {
    Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the icon
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Sun time icon",
            modifier = Modifier.size(35.dp)
        )
        // Display the time
        Text(text = time, style = MaterialTheme.typography.body2)
    }
}