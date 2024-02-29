package com.foss.auth_presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.auth_domain.models.LoginRequestParamsModel
import com.foss.auth_domain.models.SocialLoginRequestParams
import com.foss.auth_domain.use_case.GetBioMetricsUseCase
import com.foss.auth_domain.use_case.GetLoginUseCase
import com.foss.auth_domain.use_case.GetShowBioMetricCardUseCase
import com.foss.auth_domain.use_case.GetSocialLoginUseCase
import com.foss.core.common.validations.MFMKValidations
import com.foss.core.models.Resource
import com.foss.shared.domain.use_cases.GetUserDataFromDataStoreUseCase
import com.foss.shared.domain.use_cases.SetUserDataToDataStoreUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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
class LoginScreenViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase,
    private val getSocialLoginUseCase: GetSocialLoginUseCase,
    private val getSetUserDataToDataStoreUseCase: SetUserDataToDataStoreUseCase,
    private val getUserDataFromDataStoreUseCase: GetUserDataFromDataStoreUseCase,
    private val provideBioMetricsUseCase: GetBioMetricsUseCase,
    private val getShowBioMetricCardUseCase: GetShowBioMetricCardUseCase,
) : ViewModel() {
    private val TAG = "TAG"
    val firebaseAuth = FirebaseAuth.getInstance()
    var canShowBioMetrics by mutableStateOf(false)

    // State variables using MutableState
    var _loading by mutableStateOf(false)
    var toastMessage by mutableStateOf("")
    var emailErrorText: String by mutableStateOf("")
    var isEmailError: Boolean by mutableStateOf(false)
    var passwordErrorText: String by mutableStateOf("")
    var isPasswordError: Boolean by mutableStateOf(false)

    // Private state variables for email and password
    private var _email: String by mutableStateOf("")
    private var _password: String by mutableStateOf("")

    // Getters for email and password

    val loading: Boolean
        get() = _loading

    val email: String
        get() = _email

    val password: String
        get() = _password


    /**
     * Updates the email value.
     *
     * @param newValue New email value to be updated.
     */
    fun onEmailValueChange(newValue: String) {
        _email = newValue
    }

    /**
     * Updates the password value.
     *
     * @param newValue New password value to be updated.
     */
    fun onPasswordValueChange(newValue: String) {
        _password = newValue
    }

    /**
     * Displays a toast message.
     *
     * @param message Message to be displayed in the toast.
     */
    fun showToast(message: String) {
        toastMessage = message
    }

    /**
     * Handles the action when the Sign-In button is pressed.
     *
     * @param navigateTo Callback function to navigate to a specific destination.
     */
    fun onSignInButtonPressed(
        callback: () -> Unit,
    ) {
        // Field validations for email and password
        if (!MFMKValidations.isValidEmail(_email)) {
            isEmailError = true
            emailErrorText = "Please provide a valid email"
        } else {
            isEmailError = false
        }

        if (!MFMKValidations.isPasswordValid(_password)) {
            isPasswordError = true
            passwordErrorText = "Please provide a valid password"
        } else {
            isPasswordError = false
        }
        // Execute login functionality if no validation errors
        if (!isEmailError && !isPasswordError) {
            getLoginUseCase(
                LoginRequestParamsModel(
                    email = _email, password = _password
                )
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loading = true
                    }

                    is Resource.Success -> {
                        _loading = false
                        if (it.data != null) {
                            getSetUserDataToDataStoreUseCase(it.data!!.copy(password = password))
                            callback()

                        }
                    }

                    is Resource.Error -> {
                        _loading = false
                        Timber.tag(TAG).e("Error from LoginScreenViewModel: ${it.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onGoogleLoginPressed(account: GoogleSignInAccount?, onSignInSuccessCallBack: () -> Unit) {
        _loading = true
        if (account != null) {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign-in successful, handle the authenticated user
                        val firebaseUser = firebaseAuth.currentUser
                        getSocialLoginUseCase(
                            SocialLoginRequestParams(token = account.idToken.toString())
                        ).onEach {
                            when (it) {
                                is Resource.Loading -> {
                                    _loading = true
                                }

                                is Resource.Success -> {
                                    _loading = false
                                    onSignInSuccessCallBack()
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
            _loading = false
        }
    }

    suspend fun ll(context: FragmentActivity) {
        canShowBioMetrics = getShowBioMetricCardUseCase(context)
    }

    fun doBioMetricsAuth(context: FragmentActivity, callback: () -> Unit) {
        provideBioMetricsUseCase(
            context
        ).onEach {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    if (it.data!!.data == true) {

                        val userData = getUserDataFromDataStoreUseCase()
                        if (userData?.email != null && userData.email!!.isNotBlank()) {
                            getLoginUseCase(
                                LoginRequestParamsModel(
                                    email = userData.email!!, password = userData.password!!
                                )
                            ).onEach { it2 ->
                                when (it2) {
                                    is Resource.Loading -> {
                                        _loading = true
                                    }

                                    is Resource.Success -> {
                                        _loading = false
                                        if (it2.data != null) {
                                            getSetUserDataToDataStoreUseCase(it2.data!!)
                                            callback()
                                        }
                                    }

                                    is Resource.Error -> {
                                        _loading = false
                                        Timber.tag(TAG).e("Error: ${it.message}")
                                    }
                                }
                            }.launchIn(viewModelScope)
                        }

                    }
                    showToast(it.data?.message.toString())
                }

                is Resource.Error -> {
                    Timber.tag(TAG).e("Error: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)


    }

}