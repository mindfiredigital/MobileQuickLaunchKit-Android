package com.foss.auth_presentation.screens.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.auth_domain.models.ForgotPasswordRequestParams
import com.foss.auth_domain.use_case.GetForgotPasswordUseCase
import com.foss.core.models.Resource
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.utility.MQLKValidations
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
class ForgotPasswordViewModel @Inject constructor(
    private val getForgotPasswordUseCase: GetForgotPasswordUseCase,
) : ViewModel() {

    // Private state variables for email and password
    private var _loading by mutableStateOf(false)
    private var _toastMessage by mutableStateOf("")
    private var _emailErrorText: String by mutableStateOf("")
    private var _isEmailError: Boolean by mutableStateOf(false)
    private var _email: String by mutableStateOf("")
    private var _password: String by mutableStateOf("")
    private val TAG = "TAG"

    val loading: Boolean
        get() = _loading
    val toastMessage: String
        get() = _toastMessage
    val emailErrorText: String
        get() = _emailErrorText
    val isEmailError: Boolean
        get() = _isEmailError

    val email: String
        get() = _email

    /**
     * Updates the email value.
     *
     * @param newValue New email value to be updated.
     */
    fun onEmailValueChange(newValue: String) {
        _email = newValue
    }

    /**
     * Displays a toast message.
     *
     * @param message Message to be displayed in the toast.
     */
    fun showToast(message: String) {
        _toastMessage = message
    }

    /**
     * Handles the action when the Sign-In button is pressed.
     *
     * @param navigateTo Callback function to navigate to a specific destination.
     */
    fun onSendOTPBtnPressed(
        navigateTo: (String) -> Unit,
    ) {
        // Field validations for email and password
        if (!MQLKValidations.isValidEmail(_email)) {
            _isEmailError = true
            _emailErrorText = "Please provide a valid email"
        } else {
            _isEmailError = false
        }

        // Execute login functionality if no validation errors
        if (!isEmailError) {
            getForgotPasswordUseCase(
                ForgotPasswordRequestParams(email = _email)
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loading = true
                    }

                    is Resource.Success -> {
                        _loading = false
                        showToast(it.data?.message.toString())
                        navigateTo(MQLKScreens.OTPVerification.route)
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