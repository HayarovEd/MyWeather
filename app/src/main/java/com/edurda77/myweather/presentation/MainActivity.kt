package com.edurda77.myweather.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.edurda77.myweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WeatherViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getGpsData(viewModel)
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    viewModel.getShowedData(isAccess = false)
                }
                else -> {
                    viewModel.getShowedData(isAccess = false)
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )


        viewModel.state.observe(this) { result ->
            when (result) {
                is MainActivityState.Loading -> {

                }
                is MainActivityState.SuccessLocationAndWeather -> {
                    binding.locationData.longitudeTv.text = "Долгота ${result.longitude}"
                    binding.locationData.latitudeTv.text = "Широта ${result.latitude}"
                    binding.locationData.altitudeTv.text = "Высота ${result.altitude}"
                    binding.locationData.accuracyTv.text = "Точность ${result.accuracy}"
                    binding.locationData.providerTv.text = "Провайдер ${result.provider}"
                    binding.weatherData.temperatureTv.text =
                        "Температура ${result.weather?.temperature}"
                    binding.weatherData.pressureTv.text = "Температура ${result.weather?.pressure}"
                    binding.weatherData.sunRiseTv.text = "Восход Cолнца ${result.weather?.sunRise}"
                    binding.weatherData.sunSetTv.text = "Заход Cолнца ${result.weather?.sunSet}"
                    binding.weatherData.moonRiseTv.text =
                        "Восход Луны ${result.astronomy?.moonRise}"
                    binding.weatherData.moonSetTv.text = "Заход Луны ${result.astronomy?.moonSet}"
                    binding.weatherData.phaseTv.text = "Фаза Луны ${result.weather?.phaseMoon}"
                    binding.errorTv.isVisible = false
                }
                is MainActivityState.Error -> {
                    binding.locationMc.isVisible = false
                    binding.weatherMc.isVisible = false
                    binding.errorTv.isVisible = true
                    binding.errorTv.text = result.message
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getGpsData(viewModel: WeatherViewModel) {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        val locationListener = LocationListener { location ->
            location.provider?.let {
                viewModel.getShowedData(
                    isAccess = true,
                    longitude = location.longitude,
                    latitude = location.latitude,
                    altitude = location.altitude,
                    accuracy = location.accuracy,
                    provider = it
                )
            }
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager?.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            500L,
            0f,
            locationListener
        )
    }
}