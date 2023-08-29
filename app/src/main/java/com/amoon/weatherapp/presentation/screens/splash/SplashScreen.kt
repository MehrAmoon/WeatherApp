package com.amoon.weatherapp.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.navigation.AppScreens
import com.amoon.weatherapp.presentation.screens.components.AnimatedWeatherType
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableSun
import com.amoon.weatherapp.presentation.ui.utils.AppColors
import kotlinx.coroutines.delay

/**
 * Composable function to display a splash screen with animation and navigation to the main screen.
 *
 * @param navController NavController to handle navigation actions.
 */
@Composable
fun SplashScreen(navController: NavController) {
    // Default city for navigation
    val defaultCity = "Berlin"

    // Animatable value for scaling animation
    val scale = remember {
        Animatable(initialValue = 0f)
    }

    // Animation and navigation effects
    LaunchedEffect(key1 = true, block = {
        // Scale animation with overshoot effect
        scale.animateTo(
            targetValue = .9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        // Delay before navigating to main screen
        delay(timeMillis = 2000L)
        navController.navigate(route = "${AppScreens.MainScreen.name}/$defaultCity")
    })

    // UI layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animated surface with text and icon
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(330.dp)
                .scale(scale = scale.value),
            shape = CircleShape,
            color = Color.Gray,
            border = BorderStroke(width = 2.dp, color = AppColors.LightGray)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Text "Weather" with light gray color
                Text(
                    text = "Weather",
                    style = MaterialTheme.typography.h5,
                    color = AppColors.LightGray
                )

                // Animated sun icon
                AnimatableSun(
                    Modifier
                        .size(150.dp)
                        .padding(4.dp)
                )
            }
        }
    }
}