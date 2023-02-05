package com.edurda77.myweather.di

import com.edurda77.myweather.data.location.LocatorDefault
import com.edurda77.myweather.domain.location.Locator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(locatorDefault: LocatorDefault): Locator
}