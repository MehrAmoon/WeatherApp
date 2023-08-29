package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableCloud
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.Cloud
import com.amoon.weatherapp.presentation.screens.utils.WeatherType
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableRains
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableSnow
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableSun
import com.amoon.weatherapp.presentation.screens.components.weatherAnimatedIcons.AnimatableThunder

/**
 * Composable function to display an animated weather icon based on the provided weather type.
 *
 * @param weather The weather type to determine which animated icon to display.
 */
@Composable
fun AnimatedWeatherType(weather: String?) {
    when (weather) {
        WeatherType.Rain.name -> WeatherAnimatableIcon.RainAnimatableIcon()
        WeatherType.Clouds.name -> WeatherAnimatableIcon.CloudyAnimatableIcon()
        WeatherType.Clear.name -> WeatherAnimatableIcon.MostlyClearAnimatableIcon()
        WeatherType.Snow.name -> WeatherAnimatableIcon.SnowAnimatableIcon()
        WeatherType.Sun.name -> WeatherAnimatableIcon.SunnyAnimatableIcon()
        WeatherType.Thunder.name -> WeatherAnimatableIcon.ThunderStormAnimatableIcon()
        WeatherType.HeavyRain.name -> WeatherAnimatableIcon.HeavyRainAnimatableIcon()
    }
}



/**
 * Object containing Composable functions for displaying various animated weather icons.
 */
object WeatherAnimatableIcon {
    /**
     * Composable function for displaying an animated sun icon.
     */
    val SunnyAnimatableIcon = @Composable {
        AnimatableSun(
            Modifier
                .size(80.dp)
                .padding(4.dp)
        )
    }

    /**
     * Composable function for displaying an animated sun icon with clouds.
     */
    val MostlyClearAnimatableIcon = @Composable {
        Box(
            Modifier
                .size(80.dp)
        ) {
            AnimatableSun(
                Modifier
                    .size(80.dp)
                    .offset(6.dp)
            )
            Cloud(
                Modifier
                    .size(32.dp)
                    .offset(16.dp, 36.dp)
            )
        }
    }

    /**
     * Composable function for displaying an animated cloud icon.
     */
    val CloudyAnimatableIcon = @Composable {
        AnimatableCloud(
            Modifier
                .testTag("Animated Cloud")
                .size(80.dp)
                .padding(6.dp),
            800
        )
    }

    /**
     * Composable function for displaying an animated rain icon with clouds.
     */
    val RainAnimatableIcon = @Composable {
        Box(Modifier.size(80.dp)) {
            AnimatableRains(
                Modifier
                    .size(50.dp)
                    .offset(10.dp, 16.dp),
                true
            )
            Cloud(
                Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }

    /**
     * Composable function for displaying an animated heavy rain icon with clouds.
     */
    val HeavyRainAnimatableIcon = @Composable {
        Box(Modifier.size(80.dp)) {
            AnimatableRains(
                Modifier
                    .size(50.dp)
                    .offset(10.dp, 16.dp)
            )
            Cloud(
                Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }

    /**
     * Composable function for displaying an animated snow icon with clouds.
     */
    val SnowAnimatableIcon = @Composable {
        Box(Modifier.size(80.dp)) {
            AnimatableSnow(
                Modifier
                    .size(40.dp)
                    .offset(6.dp, 16.dp),
            )
            Cloud(
                Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }

    /**
     * Composable function for displaying an animated thunderstorm icon with clouds.
     */
    val ThunderStormAnimatableIcon = @Composable {
        Box(Modifier.size(80.dp)) {
            Cloud(
                Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            )
            AnimatableThunder(
                Modifier
                    .size(40.dp)
                    .offset(30.dp, 36.dp),
                300
            )
        }
    }
}
