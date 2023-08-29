package com.amoon.weatherapp.presentation.screens.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Formats a timestamp to a string in the format "EEE, MMM d".
 *
 * @param timestamp Timestamp in seconds.
 * @return Formatted date string.
 */
fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

/**
 * Formats a timestamp to a string in the format "hh:mm:aa".
 *
 * @param timestamp Timestamp in seconds.
 * @return Formatted time string.
 */
fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

/**
 * Formats a decimal number to a string with no decimal places.
 *
 * @param decimal Decimal number.
 * @return Formatted decimal string.
 */
fun formatDecimal(decimal: Double): String = "%.0f".format(decimal)