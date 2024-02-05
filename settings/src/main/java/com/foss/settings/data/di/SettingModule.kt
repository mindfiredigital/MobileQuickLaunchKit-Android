package com.foss.settings.data.di

import com.foss.auth_data.source.remote.SettingApi
import com.foss.core.network.createRetrofitWithMoshi
import com.foss.core.utils.AppConstant
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {

    /**
     * Provides an instance of [AuthApi] for making API calls related to authentication.
     *
     * @param baseUrl The base URL of the Auth API.
     * @param okHttpClient The OkHttpClient instance for making HTTP requests.
     * @param moshi The Moshi instance for JSON serialization/deserialization.
     * @return An instance of [AuthApi].
     */
    @Provides
    @Singleton
    fun provideSettingApi(
        @Named(value = AppConstant.Endpoints.BASE_URL_NAMED) baseUrl: String,
        @Named(value = AppConstant.Endpoints.OK_HTTP_CLIENT_WITH_TOKEN) okHttpClient: OkHttpClient,
        moshi: Moshi
    ): SettingApi {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
    }


}