package com.foss.utility

import android.content.Context
import android.content.Intent


/**
 * Utility class for common operations.
 */
open class MQLKUtilities {

    companion object {

        /**
         * Shares plain text content using the Android sharing functionality.
         * @param context The context of the application.
         * @param value The plain text content to be shared.
         */
        fun sharePlainText(context: Context, value: String) {
            // Create an intent for sending data
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND  // Set action to ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, value) // Put the text content in the intent
                type = "text/plain"  // Set the MIME type to text/plain
            }

            // Create a chooser for the sharing intent
            val shareIntent = Intent.createChooser(sendIntent, null)

            // Start the activity with the sharing intent
            context.startActivity(shareIntent)
        }
    }
}
