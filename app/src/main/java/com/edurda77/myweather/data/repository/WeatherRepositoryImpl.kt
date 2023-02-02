package com.edurda77.myweather.data.repository

import com.edurda77.myweather.data.mapper.toAstronomy
import com.edurda77.myweather.data.mapper.toTodayWeather
import com.edurda77.myweather.data.remote.AstronomyApi
import com.edurda77.myweather.data.remote.WeatherApi
import com.edurda77.myweather.domain.model.Astronomy
import com.edurda77.myweather.domain.model.TodayWeather
import com.edurda77.myweather.domain.repository.WeatherRepository
import com.edurda77.myweather.domain.utils.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val astronomyApi: AstronomyApi,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(long: Double, lat: Double): Resource<TodayWeather> {
        return try {
            Resource.Success(
                data = weatherApi.getCurrentWeather(longitude = long, latitude = lat)
                    .toTodayWeather()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }

    }

    override suspend fun getMoonData(long: Double, lat: Double): Resource<Astronomy> {
        return try {
            Resource.Success(
                data = astronomyApi.getAstronomy(longitude = long, latitude = lat).toAstronomy()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }


}