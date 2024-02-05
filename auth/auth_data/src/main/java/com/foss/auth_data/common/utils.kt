package com.foss.auth_data.common

import com.foss.auth_data.models.ForgotPasswordResponseDTO
import com.foss.auth_data.models.LoginResponseDTO
import com.foss.auth_data.models.OtpVerificationResponseDTO
import com.foss.auth_data.models.PasswordResetResponseDTO
import com.foss.auth_data.models.SignupResponseDTO
import com.foss.auth_data.models.SocialLoginResponseDTO
import com.foss.auth_data.models.SocialSignupResponseDTO
import com.foss.core.models.CustomResponseModel
import com.foss.core.models.UserModel

fun LoginResponseDTO.toDomainModel(): UserModel {
    return UserModel(
        email = this.data?.email,
        full_name = this.data?.full_name,
        refreshToken = this.data?.refresh_token,
        token = this.data?.token,
        phoneNumber = this.data?.phone_number,
        profileSignedUrl = this.data?.profileSignedUrl,
    )
}

fun SignupResponseDTO.toDomainModel(): UserModel {
    return UserModel(
        email = this.data?.email,
        full_name = this.data?.full_name,
        refreshToken = this.data?.refresh_token,
        token = this.data?.token,
        phoneNumber = this.data?.phone_number,
        profileSignedUrl = this.data?.profileSignedUrl,
    )
}

fun ForgotPasswordResponseDTO.toDomainModel(): CustomResponseModel<Any?> {
    return CustomResponseModel(message = this.message, data = null);
}

fun OtpVerificationResponseDTO.toDomainModel(): CustomResponseModel<Any?> {
    return CustomResponseModel(message = this.message, data = null)
}

fun PasswordResetResponseDTO.toDomainModel(): CustomResponseModel<Any?> {
    return CustomResponseModel(message = this.message, data = null)
}


fun SocialSignupResponseDTO.toDomainModel(): CustomResponseModel<UserModel?> {
    return CustomResponseModel(
        message = this.message, data = UserModel(
            email = this.data?.email,
            full_name = this.data?.full_name,
            refreshToken = this.data?.refresh_token,
            token = this.data?.token,
            phoneNumber = this.data?.phone_number,
            profileSignedUrl = this.data?.profileSignedUrl,
        )
    )
}

fun SocialLoginResponseDTO.toDomainModel(): CustomResponseModel<UserModel?> {
    return CustomResponseModel(
        message = this.message, data = UserModel(
            email = this.data?.email,
            full_name = this.data?.full_name,
            refreshToken = this.data?.refresh_token,
            token = this.data?.token,
            phoneNumber = this.data?.phone_number,
            profileSignedUrl = this.data?.profileSignedUrl,
        )
    )
}