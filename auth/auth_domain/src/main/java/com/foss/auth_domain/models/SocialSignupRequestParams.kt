package com.foss.auth_domain.models

data class SocialSignupRequestParams(
    val token: String,
    val email: String,
    val full_name: String
)
