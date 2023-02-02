package com.edurda77.myweather.data

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.convertToTimeString(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm:ss")
    return this.format(formatter)
}