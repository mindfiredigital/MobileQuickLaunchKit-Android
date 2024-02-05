package com.foss.auth_data.source.remote

import com.foss.auth_data.models.ForgotPasswordResponseDTO
import com.foss.auth_data.models.LoginResponseDTO
import com.foss.auth_data.models.OtpVerificationResponseDTO
import com.foss.auth_data.models.PasswordResetResponseDTO
import com.foss.auth_data.models.SignupResponseDTO
import com.foss.auth_data.models.SocialLoginResponseDTO
import com.foss.auth_data.models.SocialSignupResponseDTO
import com.foss.auth_domain.models.ForgotPasswordRequestParams
import com.foss.auth_domain.models.LoginRequestParamsModel
import com.foss.auth_domain.models.PasswordResetRequestParams
import com.foss.auth_domain.models.SignUpRequestParamsModel
import com.foss.auth_domain.models.SocialLoginRequestParams
import com.foss.auth_domain.models.SocialSignupRequestParams
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 * @Description: Interface representing the Auth API endpoints.
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */
interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body requestParamsMode: LoginRequestParamsModel
    ): LoginResponseDTO

    @POST("auth/sign_up")
    suspend fun signup(
        @Body requestParamsMode: SignUpRequestParamsModel
    ): SignupResponseDTO

    @POST("auth/password/forgot")
    suspend fun forgotPassword(
        @Body forgotPasswordRequestParams: ForgotPasswordRequestParams
    ): ForgotPasswordResponseDTO

    @GET("auth/password/verification/otp")
    suspend fun otpVerification(@Query("code") code: String): OtpVerificationResponseDTO

    @POST("auth/password/reset")
    suspend fun restPassword(@Body passwordResetRequestParams: PasswordResetRequestParams): PasswordResetResponseDTO

    @POST("auth/social/signup")
    suspend fun socialSignup(@Body socialSignupRequestParams: SocialSignupRequestParams): SocialSignupResponseDTO

    @POST("auth/social/login")
    suspend fun socialLogin(@Body socialLoginRequestParams: SocialLoginRequestParams): SocialLoginResponseDTO

}