package com.foss.core.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */

/**
 * Interceptor implementation for intercepting and modifying outgoing HTTP requests.
 * Such as adding headers, modifying the URL, or logging the request details
 */
class HttpRequestInterceptor : Interceptor {
    /**
     * Intercepts and modifies the outgoing request before it is sent to the server.
     *
     * @param chain The interceptor chain that allows proceeding with the request.
     * @return The response received from the server.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request from the chain
        val originalRequest = chain.request()

        // Create a new request with the same URL as the original request
        val request = originalRequest.newBuilder().url(originalRequest.url).build()

        // Log the modified request details for debugging
        Timber.d(request.toString())

        // Proceed with the modified request and return the server's response
        return chain.proceed(request)
    }
}