package com.amoon.weatherapp.presentation.di

import com.amoon.weatherapp.data.repositories.DatabaseRepositoryImpl
import com.amoon.weatherapp.data.repositories.NetworkRepositoryImpl
import com.amoon.weatherapp.domain.repositories.DatabaseRepository
import com.amoon.weatherapp.domain.repositories.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// Module to provide repository implementations using Hilt
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    // Binds NetworkRepositoryImpl to NetworkRepository
    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    // Binds DatabaseRepositoryImpl to DatabaseRepository
    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}