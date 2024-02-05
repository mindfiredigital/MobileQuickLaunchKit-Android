package com.foss.settings.data.di


import com.foss.auth_data.source.remote.SettingApi
import com.foss.settings.data.repository.SettingsRepositoryImplementation
import com.foss.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingRepo {

    @Provides
    @Singleton
    fun provideSettingRepo(api: SettingApi,): SettingsRepository {
        return SettingsRepositoryImplementation(api)
    }
}