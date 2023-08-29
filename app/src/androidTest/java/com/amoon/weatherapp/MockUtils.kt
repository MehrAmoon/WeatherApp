package com.amoon.weatherapp

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import com.amoon.weatherapp.presentation.models.UIModelCity
import com.amoon.weatherapp.presentation.models.UIModelCords
import com.amoon.weatherapp.presentation.models.UIModelFeelsLike
import com.amoon.weatherapp.presentation.models.UIModelTemp
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherObject
import com.amoon.weatherapp.presentation.viewmodel.FavoriteViewModel
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

object MockUtils {

    //UIModelResponse
    val uIModelCords = UIModelCords(52.2, 52.2)
    val uIModelCity = UIModelCity(uIModelCords, "DE", 10, "Berlin", 100, 7200)
    val uIModelFeelsLike = UIModelFeelsLike(0.8, 0.8, 0.8, 0.8)
    val uIModelTemp = UIModelTemp(0.9, 0.9, 0.9, 0.9, 0.9, 0.9)
    val uIModelWeatherObject = UIModelWeatherObject("sky is clear", "icon", 1, "main")
    val uIModelWeatherItem = UIModelWeatherItem(
        90,
        1,
        1693047600,
        uIModelFeelsLike,
        0.9,
        1,
        0.9,
        1,
        0.9,
        0.9,
        1692677414,
        1692726854,
        uIModelTemp,
        listOf(uIModelWeatherObject)
    )
    val uIModelWeather =
        UIModelWeather(uIModelCity, 7, "200", listOf(uIModelWeatherItem), 0.0511463)

    val mockFavoriteEntity = FavoriteEntity("Tehran","IR")

//    val databaseRepository = mock(DatabaseRepository::class.java)
//    val favoriteViewModel = FavoriteViewModel(databaseRepository)

    val mockNavController = TestNavHostController(ApplicationProvider.getApplicationContext())


}