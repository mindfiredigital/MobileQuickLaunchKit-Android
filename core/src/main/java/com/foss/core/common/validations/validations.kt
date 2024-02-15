package com.foss.core.common.validations

import java.util.regex.Pattern

class MFMKValidations {

    companion object {
        fun isValidEmail(email: String): Boolean {
            val emailPattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})"
            )
            return emailPattern.matcher(email).matches()
        }

        fun isPasswordValid(password: String): Boolean {
            return password.length >= 6 // Example: Minimum length requirement
        }

        fun validateFields(text: String): Boolean {
            return text.isEmpty();
        }

        fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword
        }

        fun isCodeValid(code: String): Boolean {
            return code.length == 6;
        }
    }


}