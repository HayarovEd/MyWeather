package com.edurda77.myweather.domain.model

data class TodayWeather(
    val temperature: Int,
    val pressure: Int,
    val sunRise: String,
    val sunSet: String,
    val phaseMoon: String
)
