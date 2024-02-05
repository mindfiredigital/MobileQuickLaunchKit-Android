package com.foss.utility

import android.content.Context
import android.content.Intent

open class MQLKUtilities {

    companion object {
        fun sharePlainText(context: Context) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out this awesome app\n " + "https://play.google.com/store/apps/details?id=" + context.applicationInfo.packageName
                )
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
    }


}