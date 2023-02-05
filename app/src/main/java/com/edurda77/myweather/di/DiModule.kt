package com.edurda77.myweather.di

import android.app.Application
import com.edurda77.myweather.data.remote.AstronomyApi
import com.edurda77.myweather.data.remote.WeatherApi
import com.edurda77.myweather.domain.utils.BASE_URL_GEOLOCATION
import com.edurda77.myweather.domain.utils.BASE_URL_YANDEX
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_YANDEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAstronomyApi(): AstronomyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GEOLOCATION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AstronomyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

}