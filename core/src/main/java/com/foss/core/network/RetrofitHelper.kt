package com.foss.core.network

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */

/**
 * Timeout duration for HTTP requests in seconds.
 */
private const val CLIENT_TIME_OUT = 60L

/**
 * Cache size for OkHttpClient cache in bytes.
 * The value is calculated as 10 MB (10 * 1024 * 1024 bytes).
 */
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L

/**
 * Directory name for OkHttpClient cache.
 */
private const val CLIENT_CACHE_DIRECTORY = "afc_network_cache"

/**
 * Creates and configures a Moshi instance.
 *
 * @return The configured Moshi instance.
 */
fun createMoshi(): Moshi {
    // Create a new Moshi.Builder instance
    return Moshi.Builder()
        // Add the KotlinJsonAdapterFactory to support Kotlin data classes
        .addLast(KotlinJsonAdapterFactory())
        // Build and return the Moshi instance
        .build()
}

/**
 * Creates a Cache instance for HTTP caching.
 *
 * @param context The application context.
 * @return The Cache instance.
 */
fun createCache(context: Context): Cache {
    // Create a File object representing the cache directory
    val cacheDirectory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY)

    // Create a new Cache instance with the specified cache directory and size
    return Cache(
        directory = cacheDirectory,
        maxSize = CLIENT_CACHE_SIZE
    )
}

/**
 * Creates an instance of [HttpLoggingInterceptor] with the specified logging level.
 *
 * @param isDev Specifies whether the application is in development mode. Default is true.
 * @return The created [HttpLoggingInterceptor] instance.
 */
fun createHttpLoggingInterceptor(isDev: Boolean = true): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (isDev) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

/**
 * Creates an instance of [HttpRequestInterceptor].
 *
 * @return The created [HttpRequestInterceptor] instance.
 */
fun createHttpRequestInterceptor(): HttpRequestInterceptor {
    return HttpRequestInterceptor()
}

/**
 * Creates an instance of [OkHttpClient] with the specified configurations.
 *
 * @param isCache Specifies whether caching should be enabled or not.
 * @param interceptors Additional interceptors to be added to the HTTP client.
 * @param context The application context.
 * @return The created [OkHttpClient] instance.
 */
fun createOkHttpClient(
    isCache: Boolean = false,
    vararg interceptors: Interceptor,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        // Enable caching if specified
        if (isCache) cache(createCache(context))

        // Add any additional interceptors provided
        interceptors.forEach { addInterceptor(it) }

        // Set the connection, read, and write timeouts
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)

        // Enable following SSL redirects and redirects
        followSslRedirects(true)
        followRedirects(true)

        // Enable automatic retry on connection failures
        retryOnConnectionFailure(true)
    }.build()
}

/**
 * Creates a Retrofit client with Moshi converter.
 *
 * The use of `reified T` allows us to use reflection and avoid explicitly defining the required API interface here.
 * This allows for flexibility in defining the desired API interface when using this function.
 *
 * @param okHttpClient The OkHttpClient instance to be used by Retrofit.
 * @param moshi The Moshi instance for JSON parsing and serialization.
 * @param baseUrl The base URL of the API.
 * @return The created Retrofit client instance with the specified API interface.
 */
inline fun <reified T> createRetrofitWithMoshi(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}