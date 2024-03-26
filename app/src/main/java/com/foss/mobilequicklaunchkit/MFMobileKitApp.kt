package com.foss.mobilequicklaunchkit

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 *
 * @Description: custom application class for MFMobileKitApp.
 *  This class is responsible for initializing necessary components
 *  and setting up application-wide configurations.
 *
 * @Author: Abhishek Kumar
 * Created On: 12-12-2023
 */
@HiltAndroidApp
class MFMobileKitApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        // Initialize logging only in debug builds
        if (Timber.treeCount == 0 && BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val notificationChannel = NotificationChannel(
            Consts.NOTIFICATION_CHANNEL_ID,
            Consts.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

}