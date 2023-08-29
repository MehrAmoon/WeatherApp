package com.amoon.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.domain.usecases.DatabaseUseCase
import com.amoon.weatherapp.presentation.screens.utils.formatDate
import com.amoon.weatherapp.presentation.screens.utils.formatDecimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {
    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites = _favorites.asStateFlow()


    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseUseCase.getFavorites().distinctUntilChanged()
                .collect { listOfFavorites ->
                    _favorites.value = listOfFavorites
                }
        }
    }

    fun addFavorite(favorite: FavoriteEntity) =
        viewModelScope.launch { databaseUseCase.addFavorite(favorite) }

    fun deleteFavorite(favorite: FavoriteEntity) = viewModelScope.launch {
        databaseUseCase.deleteFavorite(favorite)
    }

    fun convertToFormatDate(dt: Int) :  String {
       return formatDate(dt)
    }

    fun convertToFormatDecimal(decimal: Double) :  String {
        return formatDecimal(decimal)
    }

}