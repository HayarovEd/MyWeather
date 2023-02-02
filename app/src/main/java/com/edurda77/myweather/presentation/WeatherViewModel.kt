package com.edurda77.myweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableLiveData<MainActivityState>(MainActivityState.Loading)
    val state = _state


    fun getShowedData(
        isAccess: Boolean,
        longitude: Double = 0.0,
        latitude: Double = 0.0,
        altitude: Double = 0.0,
        accuracy: Double = 0.0,
        provider: String = ""
    ) {
        if (isAccess) {
            _state.value = MainActivityState.Success(
                longitude = longitude,
                latitude = latitude,
                altitude = altitude,
                accuracy = accuracy,
                provider = provider
            )
        } else {
            _state.value = MainActivityState.Error("Доступ сначала дай!!!")
        }
    }

}