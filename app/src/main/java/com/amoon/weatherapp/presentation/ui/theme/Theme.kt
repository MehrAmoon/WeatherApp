package com.amoon.weatherapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple40,
    primaryVariant = PurpleGrey40,
    secondary = Pink40
)

private val LightColorPalette = lightColors(
    primary = Purple80,
    primaryVariant = PurpleGrey80,
    secondary = Pink80,
    background = Color.White,
    onBackground = Color.Gray,

    )

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}