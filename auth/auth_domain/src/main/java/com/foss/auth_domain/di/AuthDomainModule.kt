package com.foss.auth_domain.di

import com.foss.auth_domain.repository.AuthRepository
import com.foss.auth_domain.use_case.GetBioMetricsUseCase
import com.foss.auth_domain.use_case.GetForgotPasswordUseCase
import com.foss.auth_domain.use_case.GetLoginUseCase
import com.foss.auth_domain.use_case.GetOtpVerificationUseCase
import com.foss.auth_domain.use_case.GetPasswordResetUseCase
import com.foss.auth_domain.use_case.GetShowBioMetricCardUseCase
import com.foss.auth_domain.use_case.GetSignUpUseCase
import com.foss.auth_domain.use_case.GetSocialLoginUseCase
import com.foss.auth_domain.use_case.GetSocialSignUpUseCase
import com.foss.core.use_cases.GetUserDataFromDataStoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AuthDomainModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): GetLoginUseCase {
        return GetLoginUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): GetSignUpUseCase {
        return GetSignUpUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(authRepository: AuthRepository): GetForgotPasswordUseCase {
        return GetForgotPasswordUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyOtpUseCase(authRepository: AuthRepository): GetOtpVerificationUseCase {
        return GetOtpVerificationUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideRestPasswordUseCase(authRepository: AuthRepository): GetPasswordResetUseCase {
        return GetPasswordResetUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideSocialSignupUseCase(authRepository: AuthRepository): GetSocialSignUpUseCase {
        return GetSocialSignUpUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideSocialLoginUseCase(authRepository: AuthRepository): GetSocialLoginUseCase {
        return GetSocialLoginUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideBioMetricsUseCase(authRepository: AuthRepository): GetBioMetricsUseCase {
        return GetBioMetricsUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideShowBioMetricCardUseCase(
        getUserDataFromDataStoreUseCase: GetUserDataFromDataStoreUseCase
    ): GetShowBioMetricCardUseCase {
        return GetShowBioMetricCardUseCase(getUserDataFromDataStoreUseCase)
    }


}