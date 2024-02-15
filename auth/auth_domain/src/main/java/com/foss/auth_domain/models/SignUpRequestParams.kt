package com.foss.auth_domain.models

data class SignUpRequestParamsModel(
    val email: String?,
    val password: String?,
    val confirm_password: String?,
    val full_name: String?,
)
