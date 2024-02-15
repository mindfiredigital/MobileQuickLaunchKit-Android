package com.foss.auth_domain.repository

import androidx.fragment.app.FragmentActivity
import com.foss.auth_domain.models.ForgotPasswordRequestParams
import com.foss.auth_domain.models.LoginRequestParamsModel
import com.foss.auth_domain.models.PasswordResetRequestParams
import com.foss.auth_domain.models.SignUpRequestParamsModel
import com.foss.auth_domain.models.SocialLoginRequestParams
import com.foss.auth_domain.models.SocialSignupRequestParams
import com.foss.core.models.CustomResponseModel
import com.foss.shared.data.model.UserModel


interface AuthRepository {
    suspend fun login(loginRequestParamsMode: LoginRequestParamsModel): UserModel?
    suspend fun signup(signUpRequestParamsModel: SignUpRequestParamsModel): UserModel?
    suspend fun forgotPassword(forgotPasswordRequestParams: ForgotPasswordRequestParams): CustomResponseModel<Any?>?

    suspend fun verifyOpt(code: String): CustomResponseModel<Any?>?

    suspend fun resetPassword(passwordResetRequestParams: PasswordResetRequestParams): CustomResponseModel<Any?>?

    suspend fun socialSignup(socialSignupRequestParams: SocialSignupRequestParams): CustomResponseModel<UserModel?>?

    suspend fun socialLogin(socialLoginRequestParams: SocialLoginRequestParams): CustomResponseModel<UserModel?>?

    suspend fun authenticateWithBiometric(context: FragmentActivity): Pair<Boolean, String>

}