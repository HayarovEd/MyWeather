package com.edurda77.myweather.di

import com.edurda77.myweather.data.repository.WeatherRepositoryImpl
import com.edurda77.myweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository


}