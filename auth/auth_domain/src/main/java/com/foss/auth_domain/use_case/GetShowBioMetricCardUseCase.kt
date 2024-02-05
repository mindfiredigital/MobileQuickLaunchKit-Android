package com.foss.auth_domain.use_case

import androidx.biometric.BiometricManager
import androidx.fragment.app.FragmentActivity
import com.foss.core.use_cases.GetUserDataFromDataStoreUseCase
import javax.inject.Inject


class GetShowBioMetricCardUseCase @Inject constructor(private val getUserDataFromDataStoreUseCase: GetUserDataFromDataStoreUseCase) {

    suspend operator fun invoke(context: FragmentActivity): Boolean {


        // Check if local data is available
        val biometricManager = BiometricManager.from(context)
        val canAuthenticateWithBiometrics =
            when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS -> true
                else -> {
                    false
                }
            }

        // Can we do Biomterics ie if device is available
        val localData = getUserDataFromDataStoreUseCase()

        // If above conditions meet, return true else return false
        return canAuthenticateWithBiometrics && localData != null && !localData.email.isNullOrBlank() && !localData.password.isNullOrBlank()


    }
}