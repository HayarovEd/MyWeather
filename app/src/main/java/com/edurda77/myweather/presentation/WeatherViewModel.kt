package com.edurda77.myweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edurda77.myweather.data.convertToTimeString
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableLiveData<MainActivityState>(MainActivityState.Loading)
    val state = _state


    init {
        getShowedData()
    }

    private fun getShowedData () {
        _state.value = MainActivityState.Success(LocalDateTime.now().convertToTimeString())
    }
}