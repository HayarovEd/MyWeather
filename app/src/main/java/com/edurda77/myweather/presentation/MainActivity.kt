package com.edurda77.myweather.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.edurda77.myweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getShowedData()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        viewModel.state.observe(this) { result ->
            when (result) {
                is MainActivityState.Loading -> {
                    binding.locationMc.isVisible = false
                    binding.weatherMc.isVisible = false
                    binding.errorTv.isVisible = false
                    binding.progress.isVisible = true
                }
                is MainActivityState.SuccessLocationAndWeather -> {
                    binding.locationMc.isVisible = true
                    binding.weatherMc.isVisible = true
                    binding.errorTv.isVisible = false
                    binding.progress.isVisible = false
                    binding.locationData.longitudeTv.text = "Долгота ${result.longitude}\u00B0"
                    binding.locationData.latitudeTv.text = "Широта ${result.latitude}\u00B0"
                    val roundedAltitude = String.format("%.2f", result.altitude)
                    binding.locationData.altitudeTv.text = "Высота ${roundedAltitude} метров"
                    binding.locationData.accuracyTv.text = "Точность ${result.accuracy} %"
                    binding.locationData.providerTv.text = "Провайдер ${result.provider}"
                    binding.weatherData.temperatureTv.text =
                        "Температура ${result.weather?.temperature}\u00B0"
                    binding.weatherData.pressureTv.text = "Давление ${result.weather?.pressure} мм рт.ст."
                    binding.weatherData.sunRiseTv.text = "Восход Cолнца ${result.weather?.sunRise}"
                    binding.weatherData.sunSetTv.text = "Заход Cолнца ${result.weather?.sunSet}"
                    binding.weatherData.moonRiseTv.text =
                        "Восход Луны ${result.astronomy?.moonRise}"
                    binding.weatherData.moonSetTv.text = "Заход Луны ${result.astronomy?.moonSet}"
                    binding.weatherData.phaseTv.text = "Фаза Луны ${result.weather?.phaseMoon}"
                }
                is MainActivityState.Error -> {
                    binding.locationMc.isVisible = false
                    binding.weatherMc.isVisible = false
                    binding.errorTv.isVisible = true
                    binding.progress.isVisible = false
                    binding.errorTv.text = result.message
                }
            }
        }

    }
}