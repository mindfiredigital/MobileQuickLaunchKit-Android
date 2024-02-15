package com.foss.shared.data.di

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import com.foss.shared.data.repository.SharedDataStoreImpl
import com.foss.shared.domain.repository.SharedDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedRepo {


    @Provides
    @Singleton
    fun provideSharedDatStoreRepo(dataStore: DataStore<Preferences>): SharedDataStore {
        return SharedDataStoreImpl(dataStore)
    }

}