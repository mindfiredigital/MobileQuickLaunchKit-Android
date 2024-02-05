package com.foss.auth_domain.models

data class PasswordResetRequestParams(
    val password: String,
    val confirm_password: String

)
