package com.foss.auth_presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.auth_domain.models.SignUpRequestParamsModel
import com.foss.auth_domain.models.SocialSignupRequestParams
import com.foss.auth_domain.use_case.GetSignUpUseCase
import com.foss.auth_domain.use_case.GetSocialSignUpUseCase
import com.foss.core.common.validations.MFMKValidations
import com.foss.core.models.Resource
import com.foss.shared.domain.use_cases.SetUserDataToDataStoreUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    val getSignUpUseCase: GetSignUpUseCase,
    val getSocialSignUpUseCase: GetSocialSignUpUseCase,
    private val getSetUserDataToDataStoreUseCase: SetUserDataToDataStoreUseCase,

    ) :
    ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "TAG"

    // Mutable state variables
    var toastMessage by mutableStateOf("")
    private var _loading: Boolean by mutableStateOf(false)
    private var _fullName: String by mutableStateOf("")
    private var _email: String by mutableStateOf("")
    private var _password: String by mutableStateOf("")
    private var _confirmPassword: String by mutableStateOf("")
    private var _fullNameErrorText: String by mutableStateOf("")
    private var _emailErrorText: String by mutableStateOf("")
    private var _passwordErrorText: String by mutableStateOf("")
    private var _confirmPasswordErrorText: String by mutableStateOf("")
    private var _isFullNameError: Boolean by mutableStateOf(false)
    private var _isEmailError: Boolean by mutableStateOf(false)
    private var _isPasswordError: Boolean by mutableStateOf(false)
    private var _isConfirmPasswordError: Boolean by mutableStateOf(false)

    // Getters for accessing state variables
    val email: String
        get() = _email

    val loading: Boolean
        get() = _loading

    val password: String
        get() = _password

    val fullName: String
        get() = _fullName

    val fullNameErrorText: String
        get() = _fullNameErrorText

    val confirmPassword: String
        get() = _confirmPassword

    val emailErrorText: String
        get() = _emailErrorText

    val passwordErrorText: String
        get() = _passwordErrorText

    val confirmPasswordErrorText: String
        get() = _confirmPasswordErrorText

    val isFullNameError: Boolean
        get() = _isFullNameError

    val isEmailError: Boolean
        get() = _isEmailError

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
     * Updates the full name value.
     *
     * @param newValue New value for the full name.
     */
    fun onFullNameValueChange(newValue: String) {
        _fullName = newValue
    }

    /**
     * Updates the email value.
     *
     * @param newValue New value for the email.
     */
    fun onEmailValueChange(newValue: String) {
        _email = newValue
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


    fun onGoogleSignInResult(
        account: GoogleSignInAccount?,
        callback: () -> Unit,
    ) {
        try {
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign-in successful, handle the authenticated user
                            val firebaseUser = firebaseAuth.currentUser
                            getSocialSignUpUseCase(
                                SocialSignupRequestParams(
                                    token = account.idToken.toString(),
                                    email = firebaseUser?.email.toString(),
                                    full_name = firebaseUser?.displayName.toString()
                                )
                            ).onEach {
                                when (it) {
                                    is Resource.Loading -> {
                                        _loading = true
                                    }

                                    is Resource.Success -> {
                                        _loading = false
                                        getSetUserDataToDataStoreUseCase(it.data!!.data!!)
                                        callback()
                                    }

                                    is Resource.Error -> {
                                        _loading = false
                                        showToast(it.message.toString())
                                        Timber.tag(TAG).e("Error: ${it.message}")
                                    }
                                }
                            }.launchIn(viewModelScope)
                        } else {
                            val exception = task.exception
                            showToast(exception?.message.toString())

                        }
                    }
            } else {
                // Google sign-in cancelled by the user
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e("Error: ${e.message}")
        }

    }


    /**
     * Handles the action when the Sign-Up button is pressed.
     *
     * @param navigateTo Callback function to navigate to a specific destination.
     */
    fun onSignUpButtonPressed(
        callback: () -> Unit,
    ) {

        // Field validations for name, email, password, and confirm password
        //
        if (MFMKValidations.validateFields(_fullName)) {
            _isFullNameError = true
            _fullNameErrorText = "THis field is required"
        } else {
            _isFullNameError = false
        }

        if (!MFMKValidations.isValidEmail(_email)) {
            _isEmailError = true
            _emailErrorText = "Please provide a valid email"
        } else {
            _isEmailError = false
        }

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
        if (!_isFullNameError && !_isEmailError && !_isPasswordError && !_isConfirmPasswordError) {

            getSignUpUseCase(
                SignUpRequestParamsModel(
                    email = _email,
                    password = _password,
                    confirm_password = _confirmPassword,
                    full_name = _fullName
                )
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loading = true
                    }

                    is Resource.Success -> {
                        _loading = false
                        callback()
                    }

                    is Resource.Error -> {
                        _loading = false
                        Timber.tag(TAG).e("Error from SignUpScreenViewModel: ${it.message}")
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

}