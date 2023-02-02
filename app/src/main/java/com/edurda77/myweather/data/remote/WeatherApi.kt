package com.edurda77.myweather.data.remote

import com.edurda77.myweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("X-Yandex-API-Key: 7afdc536-f933-474b-9ded-4c57149f6336")
    @GET("informers")
    suspend fun getCurrentWeather(
        @Query("lon") longitude : Double,
        @Query("lat") latitude : Double,
    ) : WeatherDto

}