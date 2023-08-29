package com.amoon.weatherapp.data.utils

// Sealed interface representing different states of a screen
sealed interface ScreenState<out T> {
    // Represents the loading state when data is being fetched
    object Loading : ScreenState<Nothing>

    // Represents the error state when an error occurs during data fetching
    data class Error(val throwable: Throwable) : ScreenState<Nothing>

    // Represents the success state with retrieved data
    data class Success<T>(val data: T) : ScreenState<T>
}