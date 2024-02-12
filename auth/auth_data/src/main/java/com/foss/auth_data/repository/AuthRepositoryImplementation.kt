package com.foss.auth_data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.foss.auth_data.common.toDomainModel
import com.foss.auth_data.source.remote.AuthApi
import com.foss.auth_domain.models.ForgotPasswordRequestParams
import com.foss.auth_domain.models.LoginRequestParamsModel
import com.foss.auth_domain.models.PasswordResetRequestParams
import com.foss.auth_domain.models.SignUpRequestParamsModel
import com.foss.auth_domain.models.SocialLoginRequestParams
import com.foss.auth_domain.models.SocialSignupRequestParams
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import com.foss.shared.data.model.UserModel
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImplementation @Inject constructor(
    private val apiService: AuthApi,
) :
    AuthRepository {

    override suspend fun login(loginRequestParamsMode: LoginRequestParamsModel): UserModel {
        try {


            return apiService.login(loginRequestParamsMode).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun signup(signUpRequestParamsModel: SignUpRequestParamsModel): UserModel {
        try {
            return apiService.signup(signUpRequestParamsModel).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun forgotPassword(forgotPasswordRequestParams: ForgotPasswordRequestParams): CustomResponseModel<Any?> {
        try {
            return apiService.forgotPassword(forgotPasswordRequestParams).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun verifyOpt(code: String): CustomResponseModel<Any?> {
        try {
            return apiService.otpVerification(code).toDomainModel()

        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun resetPassword(passwordResetRequestParams: PasswordResetRequestParams): CustomResponseModel<Any?> {
        try {
            return apiService.restPassword(passwordResetRequestParams).toDomainModel()

        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun socialSignup(socialSignupRequestParams: SocialSignupRequestParams): CustomResponseModel<UserModel?> {
        try {
            return apiService.socialSignup(socialSignupRequestParams).toDomainModel()

        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun socialLogin(socialLoginRequestParams: SocialLoginRequestParams): CustomResponseModel<UserModel?> {
        try {
            return apiService.socialLogin(socialLoginRequestParams).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override suspend fun authenticateWithBiometric(context: FragmentActivity): Pair<Boolean, String> =
        suspendCoroutine { continuation ->
            var isHandled = false // Track if the authentication has been handled

            try {
                val executor = context.mainExecutor
                val biometricPrompt = BiometricPrompt(
                    context,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            if (!isHandled) { // Ensure resume is called only once
                                isHandled = true
                                continuation.resume(true to "Biometric authentication succeeded!")
                            }
                        }

                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            if (!isHandled) {
                                isHandled = true
                                continuation.resume(false to errString.toString())
                            }
                        }

                        override fun onAuthenticationFailed() {
                            if (!isHandled) {
                                isHandled = true
                                continuation.resume(false to "Biometric authentication failed!")
                            }
                        }
                    })

                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric Authentication")
                    .setDescription("Place your finger on the sensor or look at the front camera to authenticate.")
                    .setNegativeButtonText("Cancel")
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                    .build()

                biometricPrompt.authenticate(promptInfo)
            } catch (e: Exception) {
                if (!isHandled) {
                    isHandled = true
                    continuation.resume(false to e.toString())
                }
            }
        }

}
