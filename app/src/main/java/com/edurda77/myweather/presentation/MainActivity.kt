package com.edurda77.myweather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.edurda77.myweather.R
import com.edurda77.myweather.data.convertToTimeString
import com.edurda77.myweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WeatherViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel.state.observe(this) {result ->
            when (result) {
                is MainActivityState.Loading -> {

                }
                is MainActivityState.Success -> {
                    //binding.currentHoursTv.text = result.localTime

                }
            }
        }

    }
}