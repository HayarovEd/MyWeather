package com.edurda77.myweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.myweather.domain.location.Locator
import com.edurda77.myweather.domain.repository.WeatherRepository
import com.edurda77.myweather.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repo: WeatherRepository,
    private val locator: Locator
) : ViewModel() {
    private val _state = MutableLiveData<MainActivityState>(MainActivityState.Loading)
    val state = _state


    fun getShowedData() {
        viewModelScope.launch {
            locator.getLocation()?.let { currentLocation ->
                val weather = async {
                    repo.getWeather(
                        lat = currentLocation.latitude,
                        long = currentLocation.longitude
                    )
                }
                val moon = async {
                    repo.getMoonData(
                        long = currentLocation.longitude,
                        lat = currentLocation.latitude
                    )
                }
                when (val resultWeather = weather.await()) {
                    is Resource.Success -> {
                        when (val result = moon.await()) {
                            is Resource.Success -> {
                                _state.value = currentLocation.provider?.let {
                                    MainActivityState.SuccessLocationAndWeather(
                                        longitude = currentLocation.longitude,
                                        latitude = currentLocation.latitude,
                                        altitude = currentLocation.altitude,
                                        accuracy = currentLocation.accuracy,
                                        provider = it,
                                        weather = resultWeather.data,
                                        astronomy = result.data
                                    )
                                }
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
            } ?: kotlin.run {
                _state.value = MainActivityState.Error("Доступ сначала дай!!!")
            }


        }
    }

}