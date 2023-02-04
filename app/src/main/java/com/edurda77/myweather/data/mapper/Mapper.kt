package com.edurda77.myweather.data.mapper

import com.edurda77.myweather.data.remote.dto.AstronomyDto
import com.edurda77.myweather.data.remote.dto.WeatherDto
import com.edurda77.myweather.domain.model.Astronomy
import com.edurda77.myweather.domain.model.TodayWeather


fun AstronomyDto.toAstronomy() : Astronomy {
    return Astronomy(
        moonRise = this.moonrise,
        moonSet = this.moonset
    )
}

fun WeatherDto.toTodayWeather() : TodayWeather {
    val phase = if (this.forecast.moonText == "full-moon") {
        "полнолуние"
    } else if (this.forecast.moonText == "decreasing-moon") {
        "убывающая Луна"
    } else if (this.forecast.moonText == "last-quarter") {
        "последняя четверть"
    } else if (this.forecast.moonText == "new-moon") {
        "новолуние"
    } else if (this.forecast.moonText == "growing-moon") {
        "растущая Луна"
    }
    else if (this.forecast.moonText == "first-quarter") {
        "первая четверть"
    }
    else {
        this.forecast.moonText
    }

    return TodayWeather(
        temperature = this.fact.temp,
        pressure = this.fact.pressureMm,
        sunRise = this.forecast.sunrise,
        sunSet = this.forecast.sunset,
        phaseMoon = phase
    )
}