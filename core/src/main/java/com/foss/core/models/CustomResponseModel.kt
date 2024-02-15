package com.foss.core.models

data class CustomResponseModel<T>(
    val message: String?,
    val data: T?,
    )