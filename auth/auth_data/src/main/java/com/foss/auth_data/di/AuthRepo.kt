package com.foss.auth_data.di

import com.foss.auth_data.repository.AuthRepositoryImplementation
import com.foss.auth_data.source.remote.AuthApi
import com.foss.auth_domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepo {

    @Provides
    @Singleton
    fun provideAuthRepo(api: AuthApi,): AuthRepository {
        return AuthRepositoryImplementation(api)
    }
}