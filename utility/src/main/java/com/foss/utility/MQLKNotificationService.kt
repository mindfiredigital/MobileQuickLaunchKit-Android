package com.foss.utility

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random


class MQLKNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(
        title: String?,
        contentText: String?,
        icon: Int,
        notificationPriority: Int = NotificationManager.IMPORTANCE_HIGH,
        channelId: String,
    ) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setSmallIcon(icon)
            .setContentText(contentText)
            .setPriority(notificationPriority)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showExpandableNotification(
        imageUrl: String, title: String,
        contentText: String,
        icon: Int,
        notificationPriority: Int = NotificationManager.IMPORTANCE_HIGH,
        channelId: String,
    ) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(icon)
            .setPriority(notificationPriority)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(
                        getBitmapFromURL(imageUrl)
                    )
            )
            .build()
        notificationManager.notify(Random.nextInt(), notification)
    }


    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection =
                url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }
}

