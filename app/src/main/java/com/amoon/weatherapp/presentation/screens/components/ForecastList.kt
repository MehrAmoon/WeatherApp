package com.amoon.weatherapp.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amoon.weatherapp.R
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.ui.theme.PurpleGrey80
import com.amoon.weatherapp.presentation.ui.utils.AppColors
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel


/**
 * Composable function for displaying a list of weather forecast items.
 *
 * @param items List of UIModelWeatherItem representing the weather forecast data.
 */
@Composable
fun ForecastList(
    items: List<UIModelWeatherItem> ,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()) {

    // Header Row with Icon and Title
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.CalendarMonth,
            contentDescription = "Icon",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(end = 5.dp),
            tint = Color.Black
        )
        Text(
            text = stringResource(R.string.this_week),
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Bold
        )
    }

    // Weather Forecast List
    Surface(
        color = AppColors.LightGray,
        shape = RoundedCornerShape(15.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp),
            content = {
                items(items) { item ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        color = Color.White,
                        shape = CircleShape.copy(
                            bottomStart = CornerSize(2.dp),
                            topEnd = CornerSize(2.dp)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = favoriteViewModel.convertToFormatDate(item.dt).subSequence(0, 3).toString(),
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            WeatherStateImage(icon = item.weather.first().icon)

                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .width(130.dp)
                                    .background(PurpleGrey80),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.weather.first().description,
                                    modifier = Modifier
                                        .padding(vertical = 1.dp, horizontal = 1.dp),
                                    style = MaterialTheme.typography.caption,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Row {
                                    Text(
                                        text = "${favoriteViewModel.convertToFormatDecimal(item.temp.max)}º",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = Color.Blue.copy(alpha = 0.7f)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${favoriteViewModel.convertToFormatDecimal(item.temp.min)}º",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = AppColors.LightGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}