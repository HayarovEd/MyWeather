package com.edurda77.myweather.data.mapper

import com.edurda77.myweather.data.remote.dto.AstronomyDto
import com.edurda77.myweather.data.remote.dto.WeatherDto
import com.edurda77.myweather.domain.model.Astronomy
import com.edurda77.myweather.domain.model.TodayWeather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.convertToTimeString(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm:ss")
    return this.format(formatter)
}

fun AstronomyDto.toAstronomy() : Astronomy {
    return Astronomy(
        moonRise = this.moonrise,
        moonSet = this.moonset
    )
}

fun WeatherDto.toTodayWeather() : TodayWeather {
    return TodayWeather(
        temperature = this.fact.temp,
        pressure = this.fact.pressureMm,
        sunRise = this.forecast.sunrise,
        sunSet = this.forecast.sunset,
        phaseMoon = this.forecast.moonText
    )
}