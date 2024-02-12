package com.foss.core.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

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
class HttpRequestInterceptor(val context: Context?, val networkHandler: NetworkHandler) :
    Interceptor {
    /**
     * Intercepts and modifies the outgoing request before it is sent to the server.
     *
     * @param chain The interceptor chain that allows proceeding with the request.
     * @return The response received from the server.
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkHandler.isConnected) {
            backgroundThreadShortToast(context, "No internet connection")
            return chain.proceed(chain.request())
        }

        return try {
            val request = chain.request()
            val response = chain.proceed(request)
            val rawJson: String = response.peekBody(Long.MAX_VALUE).string()

            if (!response.isSuccessful) {
                handleErrorResponse(context, response.code, rawJson)
            }

            response // Return the response after handling it
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
            chain.proceed(chain.request()) // Return the request in case of an exception
        }
    }
}

private fun handleErrorResponse(context: Context?, statusCode: Int, rawJson: String) {
    val jsonObject = JSONObject(rawJson)

    if (statusCode > 200) {
        backgroundThreadShortToast(context, (jsonObject.optString("message")))

    }
}

fun backgroundThreadShortToast(context: Context?, msg: String?) {
    if (context != null && msg != null) {
        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(
                context, msg, Toast.LENGTH_SHORT
            ).show()
        })
    }
}