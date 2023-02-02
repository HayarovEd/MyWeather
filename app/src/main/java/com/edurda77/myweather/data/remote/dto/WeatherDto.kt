package com.edurda77.myweather.data.remote.dto


import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("info")
    val info: Info,
    @SerializedName("now")
    val now: Int,
    @SerializedName("now_dt")
    val nowDt: String
)