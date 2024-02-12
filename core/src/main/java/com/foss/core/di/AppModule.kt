package com.foss.core.di

import android.content.Context
import com.foss.core.network.HttpRequestInterceptor
import com.foss.core.network.MFMobileKitNetworkConfig
import com.foss.core.network.NetworkHandler
import com.foss.core.network.RetrofitNetworkConfig
import com.foss.core.network.createHttpLoggingInterceptor
import com.foss.core.network.createHttpRequestInterceptor
import com.foss.core.network.createMoshi
import com.foss.core.network.createOkHttpClient
import com.foss.core.utils.AppConstant
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 * @Description: Dagger Hilt module for providing dependencies related to whole app.
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the network configuration for the API.
     * @return RetrofitNetworkConfig
     */
    @Provides
    @Singleton
    fun provideNetworkConfig(): RetrofitNetworkConfig {
        return MFMobileKitNetworkConfig()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    /**
     * Provides the base URL for the API based on the network configuration.
     * @param networkConfig The RetrofitNetworkConfig instance.
     * @return The base URL as a String.
     */
    @Singleton
    @Provides
    @Named(value = AppConstant.Endpoints.BASE_URL_NAMED)
    fun provideBaseUrl(networkConfig: RetrofitNetworkConfig): String {
        return networkConfig.baseUrl()
    }

    /**
     * Provides the Moshi instance for JSON parsing.
     * @return Moshi instance.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    /**
     * Provides the HttpRequestInterceptor instance for modifying outgoing HTTP requests.
     * @return HttpRequestInterceptor instance.
     */
    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(
        context: Context,
        networkHandler: NetworkHandler
    ): HttpRequestInterceptor {
        return createHttpRequestInterceptor(
            context,
            networkHandler
        )
    }

    /**
     * Provides the Interceptor for adding custom headers to API requests.
     * @return Interceptor instance.
     */
    @Provides
    @Named(value = AppConstant.Endpoints.HEADER_WITHOUT_TOKEN)
    fun provideAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "multipart/form-data")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Named(value = AppConstant.Endpoints.HEADER_WITH_TOKEN)
    fun provideAuthorizationInterceptorWithToken(): Interceptor {
        return Interceptor { chain ->
//            val token = runBlocking {
//                try {
//                    val userData = getUserDataFromDataStoreUseCase()
//                    userData?.token ?: ""
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    ""
//                }
//            }
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer token")
                .addHeader("Content-Type", "multipart/form-data")
                .build()
            chain.proceed(request)
        }
    }

    /**
     * Provides the HttpLoggingInterceptor for logging HTTP requests and responses.
     * @param networkConfig The RetrofitNetworkConfig instance.
     * @return HttpLoggingInterceptor instance.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: RetrofitNetworkConfig): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = networkConfig.isDev())
    }


    /**
     * Provides the OkHttpClient for making API requests.
     * @param context The application context.
     * @param httpLoggingInterceptor The HttpLoggingInterceptor instance.
     * @param httpRequestInterceptor The HttpRequestInterceptor instance.
     * @param authorizationInterceptor The Interceptor for adding custom headers.
     * @return OkHttpClient instance.
     */
    @Provides
    @Singleton
    @Named(value = AppConstant.Endpoints.OK_HTTP_CLIENT_WITHOUT_TOKEN)
    fun provideOkHttpClientWithoutToken(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor,
        @Named(value = AppConstant.Endpoints.HEADER_WITHOUT_TOKEN) authorizationInterceptor: Interceptor
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                httpRequestInterceptor,
                authorizationInterceptor,
            ),
            context = context
        )
    }

    /**
     * Provides the OkHttpClient for making API requests.
     * @param context The application context.
     * @param httpLoggingInterceptor The HttpLoggingInterceptor instance.
     * @param httpRequestInterceptor The HttpRequestInterceptor instance.
     * @param authorizationInterceptor The Interceptor for adding custom headers.
     * @return OkHttpClient instance.
     */
    @Provides
    @Singleton
    @Named(value = AppConstant.Endpoints.OK_HTTP_CLIENT_WITH_TOKEN)
    fun provideOkHttpClientWithToken(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor,
        @Named(value = AppConstant.Endpoints.HEADER_WITH_TOKEN) authorizationInterceptor: Interceptor
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                httpRequestInterceptor,
                authorizationInterceptor
            ),
            context = context
        )
    }


    @Provides
    @Singleton
    fun provideNetworkHandler(
        @ApplicationContext context: Context,
    ): NetworkHandler {
        return NetworkHandler(context)
    }


}