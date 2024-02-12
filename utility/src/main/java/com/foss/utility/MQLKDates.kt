package com.foss.utility

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Utility class for date and time formatting and parsing.
 */
open class MQLKDates {


    /**
     * Enum to specify time format options.
     */
    enum class TimeFormat {
        TWELVE_HOUR, TWENTY_FOUR_HOUR
    }

    companion object {


        @RequiresApi(Build.VERSION_CODES.O)
        fun getFormattedDateString(date: LocalDateTime, dateFormat: String): String? {

            return try {
                val formatter = DateTimeFormatter.ofPattern(dateFormat)
                return date.format(formatter)
            } catch (e: Exception) {
                // Handle formatting errors
                println("Error converting date: ${e.message}")
                null
            }
        }

        /**
         * Formats the given date string into the specified output format.
         *
         * @param inputDate The input date string to be formatted.
         * @param inputFormat The format of the input date string.
         * @param outputFormat The desired output format for the date string.
         * @return The formatted date string, or null if an error occurs during formatting.
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun changeDateFormat(
            inputDate: String, inputFormat: String, outputFormat: String
        ): String? {
            return try {
                val formatter = DateTimeFormatter.ofPattern(inputFormat)
                val parsedDate = LocalDateTime.parse(inputDate, formatter)
                val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)
                parsedDate.format(outputFormatter)
            } catch (e: Exception) {
                // Handle formatting errors
                println("Error converting date: ${e.message}")
                null
            }
        }

        /**
         * Formats the current time into the specified time format.
         *
         * @param format The desired time format (12-hour or 24-hour).
         * @param currentTime The current time to be formatted.
         * @return The formatted time string.
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun getFormattedTime(format: TimeFormat, currentTime: LocalDateTime): String {
            return when (format) {
                TimeFormat.TWELVE_HOUR -> {
                    val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
                    currentTime.format(formatter)
                }

                TimeFormat.TWENTY_FOUR_HOUR -> {
                    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                    currentTime.format(formatter)
                }
            }
        }

        /**
         * Parses the given date string into a LocalDateTime object.
         *
         * @param date The date string to be parsed.
         * @param inputFormat The format of the input date string.
         * @return The parsed LocalDateTime object, or null if an error occurs during parsing.
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun parseDateStringToDate(date: String, inputFormat: String): LocalDateTime? {
            return try {
                val formatter = DateTimeFormatter.ofPattern(inputFormat)
                val parsedDate = LocalDateTime.parse(date, formatter)
                parsedDate
            } catch (e: Exception) {
                // Handle parsing errors
                println("Error parsing date: ${e.message}")
                null
            }
        }
    }
}
