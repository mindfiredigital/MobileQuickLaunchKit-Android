package com.foss.core.network

import com.foss.core.BuildConfig
import com.foss.core.utils.AppConstant

/**
 *
 * @Description:
 *
 * AfcNetworkConfig is a concrete implementation of the abstract class [RetrofitNetworkConfig].
 * It provides the configuration details specific to the Afc network.
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */
class MFMobileKitNetworkConfig : RetrofitNetworkConfig() {

    /**
     * Returns the base URL for the API endpoints.
     *
     * @return The base URL.
     */
    override fun baseUrl(): String {
        return AppConstant.Endpoints.BASE_URL
    }

    /**
     * Returns the timeout value for network requests.
     *
     * @return The timeout value in seconds.
     */
    override fun timeOut(): Long {
        return 60L
    }

    /**
     * Returns a boolean indicating whether the app is in development mode or not.
     * This value is obtained from the [BuildConfig.DEBUG] flag.
     *
     * @return `true` if the app is in development mode, `false` otherwise.
     */
    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }


}