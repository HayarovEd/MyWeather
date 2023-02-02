package com.edurda77.myweather.domain.repository

import com.edurda77.myweather.domain.model.Astronomy
import com.edurda77.myweather.domain.model.TodayWeather
import com.edurda77.myweather.domain.utils.Resource

interface WeatherRepository {

    suspend fun getWeather(long:Double, lat: Double) : Resource<TodayWeather>

    suspend fun getMoonData(long:Double, lat: Double) : Resource<Astronomy>
}