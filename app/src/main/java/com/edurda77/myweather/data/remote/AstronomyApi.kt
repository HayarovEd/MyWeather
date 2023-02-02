package com.edurda77.myweather.data.remote

import com.edurda77.myweather.data.remote.dto.AstronomyDto
import com.edurda77.myweather.domain.utils.API_IP_GEOLOCATION
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyApi {
    @GET("astronomy")
    suspend fun getAstronomy(
        @Query("api_key") key : String = API_IP_GEOLOCATION,
        @Query("long") longitude : Double,
        @Query("lat") latitude : Double
    ) : AstronomyDto
}