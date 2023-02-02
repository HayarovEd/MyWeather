package com.edurda77.myweather.data.remote.dto

import com.edurda77.myweather.domain.utils.API_IP_GEOLOCATION
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
    @GET ("astronomy")
    suspend fun getAstronomy(
        @Query("api_key") key : String = API_IP_GEOLOCATION,
        @Query("long") longitude : Double,
        @Query("lat") latitude : Double
    ) : AstronomyDto

}