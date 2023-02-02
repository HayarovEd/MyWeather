package com.edurda77.myweather.presentation

sealed class MainActivityState{
    data class Success(
        val localTime: String
    ): MainActivityState()
    object Loading : MainActivityState()
}
