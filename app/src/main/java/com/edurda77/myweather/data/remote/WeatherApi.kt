package com.edurda77.myweather.data.remote

import com.edurda77.myweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("X-Yandex-API-Key: 5f780bcf-6764-46d6-b286-24530f63838c")
    @GET("informers")
    suspend fun getCurrentWeather(
        @Query("lon") longitude : Double,
        @Query("lat") latitude : Double,
    ) : WeatherDto

}