package com.edurda77.myweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.myweather.domain.repository.WeatherRepository
import com.edurda77.myweather.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repo: WeatherRepository) : ViewModel() {
    private val _state = MutableLiveData<MainActivityState>(MainActivityState.Loading)
    val state = _state


    fun getShowedData(
        isAccess: Boolean = false,
        longitude: Double = 0.0,
        latitude: Double = 0.0,
        altitude: Double = 0.0,
        accuracy: Float = 0f,
        provider: String = ""
    ) {
        if (isAccess) {
            viewModelScope.launch {
                val weather = async { repo.getWeather(lat = latitude, long = longitude) }
                val moon = async { repo.getMoonData(long = longitude, lat = latitude) }
                when (val resultWeather = weather.await()) {
                    is Resource.Success -> {
                        when (val result = moon.await()) {
                            is Resource.Success -> {
                                _state.value = MainActivityState.SuccessLocationAndWeather(
                                    longitude = longitude,
                                    latitude = latitude,
                                    altitude = altitude,
                                    accuracy = accuracy,
                                    provider = provider,
                                    weather = resultWeather.data,
                                    astronomy = result.data
                                )
                            }
                            is Resource.Error -> {
                                _state.value =
                                    MainActivityState.Error(result.message ?: "An unknown error")
                            }
                        }
                    }
                    is Resource.Error -> {
                        _state.value =
                            MainActivityState.Error(resultWeather.message ?: "An unknown error")
                    }
                }

            }
        } else {
            _state.value = MainActivityState.Error("Доступ сначала дай!!!")
        }
    }

}