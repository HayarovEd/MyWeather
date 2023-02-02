package com.edurda77.myweather.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

   /* @Provides
    @Singleton
    fun bindConfig(sportRemoteRepository: SportRemoteRepositoryImpl): SportRemoteRepository {
        sportRemoteRepository.initConfigs()
        return SportRemoteRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDataBase {
        return Room.databaseBuilder(
            app,
            NoteDataBase::class.java,
            "notedb.db"
        ).build()
    }*/

}