package com.amoon.weatherapp.data.database.entities


data class UnitEntity(
    val unit: String ?= "metric",
    val symbol: String ?= "C"
)
