package com.edurda77.myweather.presentation

sealed class MainActivityState{
    class Success(
        val longitude : Double,
        val latitude : Double,
        val altitude : Double,
        val accuracy : Double,
        val provider : String
    ): MainActivityState()
    class Error (val message: String) : MainActivityState()
    object Loading : MainActivityState()
}
