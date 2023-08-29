package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Composable function for displaying a single humidity, wind, or pressure item.
 *
 * @param icon Resource ID of the icon to be displayed.
 * @param amount String representing the numerical amount of the parameter.
 * @param unit String representing the unit of measurement for the parameter.
 */
@Composable
fun HWPItem(icon: Int, amount: String, unit: String) {
    Row(modifier = Modifier.padding(4.dp)) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "HWP icon",
            modifier = Modifier.size(20.dp)
        )
        Text(text = "$amount$unit", style = MaterialTheme.typography.body2)
    }
}