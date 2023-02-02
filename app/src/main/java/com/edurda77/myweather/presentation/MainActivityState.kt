package com.edurda77.myweather.presentation

import com.edurda77.myweather.domain.model.Astronomy
import com.edurda77.myweather.domain.model.TodayWeather

sealed class MainActivityState{
    class SuccessLocationAndWeather(
        val longitude : Double,
        val latitude : Double,
        val altitude : Double,
        val accuracy : Float,
        val provider : String,
        val astronomy: Astronomy? = null,
        val weather: TodayWeather? = null
    ): MainActivityState()
    class Error (val message: String) : MainActivityState()
    object Loading : MainActivityState()
}
