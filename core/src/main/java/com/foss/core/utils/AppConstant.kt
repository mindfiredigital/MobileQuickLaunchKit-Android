package com.foss.core.utils

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.io.IOException


/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */

class Config(
    @Json(name = "BASE_URL") val baseUrl: String,
    @Json(name = "Google_WEB_CLIENT_ID") val gWebClient: String,
)


object ConfigReader {
    private const val TAG = "ConfigReader"
    fun readConfig(context: Context): Boolean {

        try {
            val jsonString =
                context.assets.open("config.json").bufferedReader().use { it.readText() }
            val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            @OptIn(ExperimentalStdlibApi::class) val jsonAdapter: JsonAdapter<Config> =
                moshi.adapter<Config>()
            val config = jsonAdapter.fromJson(jsonString)

            if (config != null) {
                AppConstant.Endpoints.BASE_URL = config.baseUrl
                AppConstant.Endpoints.Google_WEB_CLIENT_ID = config.gWebClient
            } else {
                AppConstant.Endpoints.BASE_URL = "http://192.168.1.161:3001/api/v1/"
                AppConstant.Endpoints.Google_WEB_CLIENT_ID = ""
            }
            return true

        } catch (ioException: IOException) {
            Timber.tag(TAG).e("IOException while reading config file: %s", ioException.message)
        } catch (exception: Exception) {
            // Handle other exceptions
            Timber.tag(TAG).e("Exception while reading config file: %s", exception.message)
        }
        return false;
    }
}


object AppConstant {

    object Endpoints {
        const val BASE_URL_NAMED = "base_url"

        //create product flavor BASE_URL
        var BASE_URL: String = "http://192.168.1.161:3001/api/v1/"
        var Google_WEB_CLIENT_ID: String = "sfsdf"
        const val ApiSuccessStatus = "Success"
        const val ApiFailureStatus = "Error:102"
        const val Unknown_Error = "Unknown Error"
        const val HEADER_WITH_TOKEN = "header_with_token"
        const val HEADER_WITHOUT_TOKEN = "header_without_token"
        const val OK_HTTP_CLIENT_WITH_TOKEN = "OkHttpClient_With_Token"
        const val OK_HTTP_CLIENT_WITHOUT_TOKEN = "OkHttpClient_Without_Token"
    }
}

object PreferenceKeys {

    object Keys {
        const val preferenceName = ("app_preference")

    }
}