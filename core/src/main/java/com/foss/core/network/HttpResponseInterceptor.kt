package com.foss.core.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException


class HttpResponseInterceptor(
    val context: Context?, val networkHandler: NetworkHandler

) : Interceptor {
    private val TAG = "TAG"


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        // If there is no internet connection
        //
        if (!networkHandler.isConnected) {
            backgroundThreadShortToast(context, "No internet connection")
            return chain.proceed(chain.request())
        }

        try {
            val request = chain.request()
            val response = chain.proceed(request)
            val rawJson: String = response.peekBody(Long.MAX_VALUE).string()

            if (!response.isSuccessful) {
                handleErrorResponse(context, response.code, rawJson)
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e("Error: ${e.message}")
        }
        return chain.proceed(chain.request())
    }
}

private fun handleErrorResponse(context: Context?, statusCode: Int, rawJson: String) {
    val jsonObject = JSONObject(rawJson)
    when (statusCode) {
        400, 500 -> backgroundThreadShortToast(context, (jsonObject.optString("message")))
        // Add more cases as needed
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