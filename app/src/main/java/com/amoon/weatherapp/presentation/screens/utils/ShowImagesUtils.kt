package com.amoon.weatherapp.presentation.screens.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.amoon.weatherapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Default image resource for loading pictures.
 */
val DEFAULT_IMAGE = R.drawable.image_empty

/**
 * Load an image from a given URL and provide a default image while loading.
 *
 * @param url The URL of the image to load.
 * @param defaultImage The default image resource to show while loading.
 * @return A mutable state holding the loaded image as a Bitmap.
 */
@ExperimentalCoroutinesApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {

    // Mutable state to hold the loaded image
    val bitmapState: MutableState<Bitmap?> = remember {
        mutableStateOf(null)
    }

    // Load the default image while the network image is loading
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    // Load the network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}