package com.foss.auth_presentation.screens.otp_verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.auth_domain.use_case.GetOtpVerificationUseCase
import com.foss.core.common.validations.MFMKValidations
import com.foss.core.models.Resource
import com.foss.core_ui.navigation.MQLKScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel responsible for managing the logic and state of the Login screen.
 * Manages user input validation, API calls for login, and associated UI states.
 *
 * @param getLoginUseCase Use case responsible for login functionality.
 */
@HiltViewModel
class OTPVerificationViewModel @Inject constructor(
    private val getOtpVerificationUseCase: GetOtpVerificationUseCase,
) : ViewModel() {
    // Private state variables for email and password
    private val TAG = "TAG"
    private var _loading by mutableStateOf(false)
    private var _toastMessage by mutableStateOf("")
    private var _code: String by mutableStateOf("")
    private var _codeErrorText: String by mutableStateOf("")
    private var _isCodeError: Boolean by mutableStateOf(false)


    val loading: Boolean
        get() = _loading
    val toastMessage: String
        get() = _toastMessage
    val codeErrorText: String
        get() = _codeErrorText
    val code: String
        get() = _code
    val isCodeError: Boolean
        get() = _isCodeError


    /**
     * Displays a toast message.
     *
     * @param message Message to be displayed in the toast.
     */
    fun showToast(message: String) {
        _toastMessage = message
    }

    fun onOtpChange(code: String) {
        _code = code
    }


    /**
     * Handles the action when the Sign-In button is pressed.
     *
     * @param navigateTo Callback function to navigate to a specific destination.
     */
    fun onVerifyOTPBtnPressed(
        navigateTo: (String) -> Unit,
    ) {
        // Field validations for email and password
        if (!MFMKValidations.isCodeValid(_code)) {
            _isCodeError = true
            _codeErrorText = "Please provide a valid OTP"
        } else {
            _isCodeError = false
        }

        // Execute login functionality if no validation errors
        if (!isCodeError) {
            getOtpVerificationUseCase(
                _code
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loading = true
                    }

                    is Resource.Success -> {
                        _loading = false
                        showToast(it.data?.message.toString())
                        navigateTo(MQLKScreens.SetNewPassword.route)
                    }

                    is Resource.Error -> {
                        _loading = false
                        Timber.tag(TAG).e("Error: ${it.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}