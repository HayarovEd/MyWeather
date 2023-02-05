package com.edurda77.myweather.domain.location

import android.location.Location

interface Locator {
    suspend fun getLocation(): Location?
}