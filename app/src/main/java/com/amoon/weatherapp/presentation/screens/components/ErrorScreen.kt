package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.ui.utils.AppColors
import kotlinx.coroutines.launch

/**
 * Composable function for displaying a custom Snackbar with optional retry action.
 *
 * @param message The message to be displayed in the Snackbar.
 * @param onClickRetry Callback function for the retry action.
 */
@Composable
fun SnackBar(message: String, onClickRetry: () -> Unit) {
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val showSnackbar = remember { mutableStateOf(true) }

    // Show the Snackbar using LaunchedEffect and rememberCoroutineScope
    LaunchedEffect(showSnackbar.value) {
        coroutineScope.launch {
            snackState.showSnackbar(message, duration = SnackbarDuration.Long)
        }
    }

    // Display the SnackbarHost with the custom Snackbar layout
    SnackbarHost(
        modifier = Modifier,
        hostState = snackState
    ) { snackbarData: SnackbarData ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp, start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CustomSnackBar(
                snackbarData.message,
                isRtl = false,
                containerColor = Color.Gray,
                onClickRetry = { onClickRetry.invoke() }
            )
        }
    }
}


/**
 * Composable function for displaying a custom Snackbar.
 *
 * @param message The message to be displayed in the Snackbar.
 * @param isRtl Determines the layout direction (right-to-left or left-to-right).
 * @param containerColor The background color of the Snackbar container.
 * @param onClickRetry Callback function for the retry action.
 */
@Composable
fun CustomSnackBar(
    message: String,
    isRtl: Boolean = false,
    containerColor: Color = Color.Black,
    onClickRetry: () -> Unit
) {
    Snackbar(backgroundColor = containerColor) {
        CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    Modifier.padding(bottom = 10.dp, top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 5.dp, bottom = 10.dp),
                        imageVector = Icons.Rounded.BrokenImage,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = message
                    )

                }
                AnimationButton(
                    text = stringResource(R.string.retry),
                    onClickRetry = {
                        onClickRetry()
                    })
            }
        }
    }
}



/**
 * Composable function for displaying an animated button with a text label.
 *
 * @param text The text label for the button.
 * @param onClickRetry Callback function for the button's click action.
 * @param animationDuration Duration of the button's animation.
 * @param scaleDown The scale factor to apply during the animation's downscaling.
 */
@Composable
fun AnimationButton(
    text: String,
    onClickRetry: () -> Unit,
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f
) {

    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Box(
        modifier = Modifier
            .scale(scale = scale.value)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(interactionSource = interactionSource, indication = null) {
                onClickRetry()
                coroutineScope.launch {
                    scale.animateTo(
                        scaleDown,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(animationDuration),
                    )
                }
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 36.dp, vertical = 12.dp),
            fontSize = 10.sp,
            color = AppColors.Yellow,
            fontWeight = FontWeight.Medium
        )
    }
}