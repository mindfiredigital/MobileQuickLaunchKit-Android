package com.foss.core.network

/**
 *
 * @Description:
 * Abstract class defining the configuration for Retrofit network operations.
 * Subclasses must provide implementations for the baseUrl() and timeOut() functions.
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */
abstract class RetrofitNetworkConfig {

    /**
     * Returns the base URL for the Retrofit client.
     * Subclasses must override this function and provide the appropriate base URL.
     */
    abstract fun baseUrl(): String

    /**
     * Returns the timeout duration for network requests in seconds.
     * Subclasses must override this function and provide the desired timeout value.
     */
    abstract fun timeOut(): Long

    /**
     * Returns a boolean indicating whether the configuration is for development purposes.
     * By default, it returns false, indicating a non-development environment.
     * Subclasses can override this function to customize the behavior based on the environment.
     */
    open fun isDev() = false
}