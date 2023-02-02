package com.edurda77.myweather.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AstronomyDto(
    @SerializedName("current_time")
    val currentTime: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("day_length")
    val dayLength: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("moon_altitude")
    val moonAltitude: Double,
    @SerializedName("moon_azimuth")
    val moonAzimuth: Double,
    @SerializedName("moon_distance")
    val moonDistance: Double,
    @SerializedName("moon_parallactic_angle")
    val moonParallacticAngle: Double,
    @SerializedName("moon_status")
    val moonStatus: String,
    @SerializedName("moonrise")
    val moonrise: String,
    @SerializedName("moonset")
    val moonset: String,
    @SerializedName("solar_noon")
    val solarNoon: String,
    @SerializedName("sun_altitude")
    val sunAltitude: Double,
    @SerializedName("sun_azimuth")
    val sunAzimuth: Double,
    @SerializedName("sun_distance")
    val sunDistance: Double,
    @SerializedName("sun_status")
    val sunStatus: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)