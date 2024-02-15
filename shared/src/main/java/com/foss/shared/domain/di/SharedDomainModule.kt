package com.foss.shared.domain.di

import com.foss.shared.domain.repository.SharedDataStore
import com.foss.shared.domain.use_cases.GetClearDataStoreUseCase
import com.foss.shared.domain.use_cases.GetUserDataFromDataStoreUseCase
import com.foss.shared.domain.use_cases.SetUserDataToDataStoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDomainModule {

    @Provides
    @Singleton
    fun provideGetUserDataFromDataStoreUseCase(dataStore: SharedDataStore): GetUserDataFromDataStoreUseCase {
        return GetUserDataFromDataStoreUseCase(dataStore)
    }

    @Provides
    @Singleton
    fun provideSetUserDataToDataStoreUseCase(dataStore: SharedDataStore): SetUserDataToDataStoreUseCase {
        return SetUserDataToDataStoreUseCase(dataStore)
    }

    @Provides
    @Singleton
    fun provideClearDataStoreUseCase(dataStore: SharedDataStore): GetClearDataStoreUseCase {
        return GetClearDataStoreUseCase(dataStore)
    }

}