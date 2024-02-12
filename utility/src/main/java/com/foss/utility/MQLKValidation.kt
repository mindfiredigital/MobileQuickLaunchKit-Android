package com.foss.utility

import java.util.regex.Pattern


/**
 * Utility class for data validation.
 */
open class MQLKValidations {
    companion object {


        /**
         * Checks whether the given input text is a valid numeric input.
         * @param text The input text to be validated.
         * @return true if the input text represents a valid numeric input, false otherwise.
         */
        fun isValidNumericInput(text: String): Boolean {
            // Check if the input text is empty
            if (text.isEmpty()) {
                return false
            }

            // Attempt to parse the input text as a numeric value
            return try {
                // Use Kotlin's built-in conversion function to parse the text to a Double
                text.toDouble()
                // If parsing succeeds, the text represents a valid numeric input
                true
            } catch (e: NumberFormatException) {
                // If parsing fails due to a NumberFormatException, the text is not a valid numeric input
                false
            }
        }


        /**
         * Checks whether the given input text is a valid email address or phone number.
         * @param text The input text to be validated.
         * @return true if the input text is a valid email address or phone number, false otherwise.
         */
        fun isValidEmailOrPhoneNumber(text: String): Boolean {
            // Check if the input text is a valid email address or phone number
            return isValidEmail(text) || isValidPhoneNumber(text)
        }


        /**
         * Function to check if the provided password matches the confirm password.
         * @param password The password to be checked.
         * @param confirmPassword The confirmation of the password.
         * @return true if the passwords match, false otherwise.
         */
        fun isPasswordMatching(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword
        }

        /**
         * Function to validate the format of an email address using a regular expression pattern.
         * @param email The email address to be validated.
         * @return true if the email address is valid, false otherwise.
         */
        fun isValidEmail(email: String): Boolean {
            val emailPattern = Pattern.compile(
                "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
            )
            return emailPattern.matcher(email).matches()
        }

        /**
         * Function to validate the format of a phone number using a regular expression pattern.
         * @param phoneNumber The phone number to be validated.
         * @return true if the phone number is valid, false otherwise.
         */
        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val sanitizedPhoneNumber = phoneNumber.replace("-", "")
            val regexPattern = Regex("\\d{10}")
            return regexPattern.matches(sanitizedPhoneNumber)
        }

        /**
         * Function to validate the format of a URL using a regular expression pattern.
         * @param url The URL to be validated.
         * @return true if the URL is valid, false otherwise.
         */
        fun isValidURL(url: String): Boolean {
            // Regular expression pattern for a comprehensive URL format
            val regexPattern =
                Regex("^(https?|ftp):\\/\\/(?:(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}|localhost|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(?::\\d+)?(?:\\/[^\\s]*)?\$")
            // Check if the provided URL matches the regex pattern
            return regexPattern.matches(url)
        }

        /**
         * Function to check if the provided password meets strong password criteria.
         * @param password The password to be validated.
         * @return true if the password is strong, false otherwise.
         */
        fun isStrongPassword(password: String): Boolean {
            val passwordPattern = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+\$).{4,}\$")
            return passwordPattern.matches(password)
        }

        /**
         * Function to validate a credit card number using the Luhn algorithm.
         * @param cardNumber The credit card number to be validated.
         * @return true if the credit card number is valid, false otherwise.
         */
        fun isValidateCreditCardNumber(cardNumber: String): Boolean {
            val sanitizedCardNumber = cardNumber.replace("\\D".toRegex(), "")

            if (sanitizedCardNumber.isEmpty() || !sanitizedCardNumber.all { it.isDigit() }) {
                return false
            }

            val reversedCardNumber = sanitizedCardNumber.reversed()

            var sum = 0
            var alternate = false

            for (char in reversedCardNumber) {
                if (!char.isDigit()) {
                    return false
                }

                var digit = char.toString().toInt()

                if (alternate) {
                    digit *= 2
                    if (digit > 9) {
                        digit -= 9
                    }
                }

                sum += digit

                alternate = !alternate
            }

            return sum % 10 == 0
        }
    }
}
