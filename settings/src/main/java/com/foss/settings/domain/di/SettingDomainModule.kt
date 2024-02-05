package com.foss.settings.domain.di


import com.foss.settings.domain.repository.SettingsRepository
import com.foss.settings.domain.use_cases.GetCreateUpdateProfileUseCase
import com.foss.settings.domain.use_cases.GetFetchUserProfileUseCase
import com.foss.settings.domain.use_cases.GetUploadProfileImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object SettingDomainModule {

    @Provides
    @Singleton
    fun provideCreateUpdateProfileUseCase(settingsRepository: SettingsRepository): GetCreateUpdateProfileUseCase {
        return GetCreateUpdateProfileUseCase(settingsRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserProfileUseCase(settingsRepository: SettingsRepository): GetFetchUserProfileUseCase {
        return GetFetchUserProfileUseCase(settingsRepository)
    }

    @Provides
    @Singleton
    fun provideGetUploadProfileImageUseCase(settingsRepository: SettingsRepository): GetUploadProfileImageUseCase {
        return GetUploadProfileImageUseCase(settingsRepository)
    }


}