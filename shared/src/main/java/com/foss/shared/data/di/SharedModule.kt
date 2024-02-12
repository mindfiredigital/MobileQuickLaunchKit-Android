package com.foss.shared.data.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.foss.shared.common.PreferenceKeys
import com.foss.shared.data.repository.SharedDataStoreImpl
import com.foss.shared.domain.repository.SharedDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object SharedModule {

    @Provides
    @Singleton
    fun provideSharedDatastore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(name = PreferenceKeys.Keys.preferenceName)
    }
}