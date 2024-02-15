package com.foss.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *
 * @Description: Check internet is on or not
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */
class NetworkHandler @Inject constructor(@ApplicationContext private val context: Context) {
    private var connectivityManager: ConnectivityManager? = null
    private var connected = false

    /**
     * Returns a boolean value indicating whether the device is connected to a network or not.
     */
    val isConnected: Boolean
        get() {
            try {
                connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkCapabilities = connectivityManager?.activeNetwork ?: return false
                val actNw =
                    connectivityManager?.getNetworkCapabilities(networkCapabilities) ?: return false
                connected = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }

                return connected
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return connected
        }
}