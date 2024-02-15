package com.foss.mobilequicklaunchkit

import android.app.Application
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
class MFMobileKitApp : Application(){

    override fun onCreate() {
        super.onCreate()

        // Initialize logging only in debug builds
        if (Timber.treeCount == 0 && BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}