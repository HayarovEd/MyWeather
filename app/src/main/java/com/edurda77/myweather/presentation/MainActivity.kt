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
                is MainActivityState.Success -> {
                    binding.longitudeTv.text = "Долгота ${result.longitude}"
                    binding.latitudeTv.text = "Широта ${result.latitude}"
                    binding.altitudeTv.text = "Высота ${result.altitude}"
                    binding.accuracyTv.text = "Точность ${result.accuracy}"
                    binding.providerTv.text = "Провайдер ${result.provider}"
                    binding.errorTv.isVisible = false
                }
                is MainActivityState.Error -> {
                    binding.longitudeTv.isVisible = false
                    binding.latitudeTv.isVisible = false
                    binding.altitudeTv.isVisible = false
                    binding.accuracyTv.isVisible = false
                    binding.providerTv.isVisible = false
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
            viewModel.getShowedData(isAccess = true, longitude = location.longitude, latitude = location.latitude)
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