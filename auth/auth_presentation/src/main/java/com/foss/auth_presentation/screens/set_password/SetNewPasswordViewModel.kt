package com.foss.auth_presentation.screens.set_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.auth_domain.models.PasswordResetRequestParams
import com.foss.auth_domain.use_case.GetPasswordResetUseCase
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
class SetNewPasswordViewModel @Inject constructor(
    private val getPasswordResetUseCase: GetPasswordResetUseCase,
) : ViewModel() {

    // Mutable state variables
    private val TAG = "TAG"
    var toastMessage by mutableStateOf("")
    private var _loading: Boolean by mutableStateOf(false)
    private var _password: String by mutableStateOf("")
    private var _confirmPassword: String by mutableStateOf("")
    private var _passwordErrorText: String by mutableStateOf("")
    private var _confirmPasswordErrorText: String by mutableStateOf("")
    private var _isPasswordError: Boolean by mutableStateOf(false)
    private var _isConfirmPasswordError: Boolean by mutableStateOf(false)

    // Getters for accessing state variables


    val loading: Boolean
        get() = _loading

    val password: String
        get() = _password

    val confirmPassword: String
        get() = _confirmPassword


    val passwordErrorText: String
        get() = _passwordErrorText

    val confirmPasswordErrorText: String
        get() = _confirmPasswordErrorText

    val isPasswordError: Boolean
        get() = _isPasswordError

    val isConfirmPasswordError: Boolean
        get() = _isConfirmPasswordError

    /**
     * Displays a toast message.
     *
     * @param message The message to display in a toast.
     */
    fun showToast(message: String) {
        toastMessage = message
    }


    /**
     * Updates the password value.
     *
     * @param newValue New value for the password.
     */
    fun onPasswordValueChange(newValue: String) {
        _password = newValue
    }

    /**
     * Updates the confirm password value.
     *
     * @param newValue New value for the confirm password.
     */
    fun onConfirmPasswordValueChange(newValue: String) {
        _confirmPassword = newValue
    }

    /**
     * Handles the action when the Sign-Up button is pressed.
     *
     * @param navigateTo Callback function to navigate to a specific destination.
     */
    fun onSubmitButtonPressed(
        navigateTo: (String) -> Unit,
    ) {

        // Field validations for name, email, password, and confirm password
        //
        if (!MFMKValidations.isPasswordValid(_password)) {
            _isPasswordError = true
            _passwordErrorText = "Please provide a valid password"
        } else {
            _isPasswordError = false
        }

        if (!MFMKValidations.isPasswordValid(_confirmPassword)) {
            _isConfirmPasswordError = true
            _confirmPasswordErrorText = "Please provide a valid password"
        } else if (!MFMKValidations.isPasswordMatch(password, _confirmPassword)) {
            _isConfirmPasswordError = true
            _confirmPasswordErrorText =
                "Passwords do not match. Please ensure both passwords match before proceeding."
        } else {
            _isConfirmPasswordError = false

        }
        // Executes sign-up functionality if there are no validation errors
        //
        if (!_isPasswordError && !_isConfirmPasswordError) {

            getPasswordResetUseCase(
                PasswordResetRequestParams(
                    password = _password,
                    confirm_password = _confirmPassword
                )
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loading = true
                    }

                    is Resource.Success -> {
                        _loading = false
                        showToast(it.data?.message.toString())
                        navigateTo(MQLKScreens.LoginScreen.route)
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