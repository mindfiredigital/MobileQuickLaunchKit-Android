package com.foss.settings.presentation.edit_profile

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foss.core.models.Resource
import com.foss.settings.domain.models.CreateUpdateProfileRequestParams
import com.foss.settings.domain.use_cases.GetCreateUpdateProfileUseCase
import com.foss.settings.domain.use_cases.GetFetchUserProfileUseCase
import com.foss.settings.domain.use_cases.GetUploadProfileImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class MQLKEditProfileScreenViewModel @Inject constructor(
    private val getCreateUpdateProfileUseCase: GetCreateUpdateProfileUseCase,
    private val getUserProfileUseCase: GetFetchUserProfileUseCase,
    private val getUploadProfileImageUseCase: GetUploadProfileImageUseCase,
) :
    ViewModel() {
    private val TAG = "TAG"
    private var _loading by mutableStateOf(false)
    private var _toastMessage by mutableStateOf("")
    private var _fullName: String by mutableStateOf("")
    private var _email: String by mutableStateOf("")
    private var _phoneNo: String by mutableStateOf("")
    private var _fullNameErrorText: String by mutableStateOf("")
    private var _emailErrorText: String by mutableStateOf("")
    private var _phoneNoErrorText: String by mutableStateOf("")
    private var _isFullNameError: Boolean by mutableStateOf(false)
    private var _isEmailError: Boolean by mutableStateOf(false)
    private var _isPhoneNoError: Boolean by mutableStateOf(false)
    private var _image: Uri? by mutableStateOf(null)


    val loading: Boolean
        get() = _loading

    val toastMessage: String
        get() = _toastMessage

    val fullName: String
        get() = _fullName

    val fullNameErrorText: String
        get() = _fullNameErrorText

    val email: String
        get() = _email
    val emailErrorText: String
        get() = _emailErrorText

    val phoneNo: String
        get() = _phoneNo
    val phoneNoErrorText: String
        get() = _phoneNoErrorText

    val isFullNameError: Boolean
        get() = _isFullNameError

    val isEmailError: Boolean
        get() = _isEmailError

    val isPhoneNoError: Boolean
        get() = _isPhoneNoError

    val image: Uri?
        get() = _image


    fun setImage(uri: Uri?) {
        _image = uri


    }

    /**
     * Displays a toast message.
     *
     * @param message Message to be displayed in the toast.
     */
    fun showToast(message: String) {
        _toastMessage = message
    }

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
    fun onPhoneNoValueChange(newValue: String) {
        _phoneNo = newValue
    }

    init {
        getUserProfile()
    }


    private fun getUserProfile() {
        getUserProfileUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _loading = true
                }

                is Resource.Success -> {
                    _loading = false
                    _email = it.data!!.data!!.email!!
                    _fullName = it.data!!.data!!.full_name!!
                    _phoneNo = it.data!!.data!!.phoneNumber!!
                }

                is Resource.Error -> {
                    _loading = false
                    showToast(it.message.toString())
                    Timber.tag(TAG).e("Error: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateUserProfile() {

        getCreateUpdateProfileUseCase(
            CreateUpdateProfileRequestParams(
                full_name = _fullName,
                phone_no = _phoneNo,
                is_update = "1"
            )
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _loading = true
                }

                is Resource.Success -> {
                    _loading = false
                    showToast(it.data!!.message.toString())
                }

                is Resource.Error -> {
                    _loading = false
                    showToast(it.message.toString())
                    Timber.tag(TAG).e("Error: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun uploadProfileImage(context: Context) {

        val inputStream: InputStream? = context.contentResolver.openInputStream(_image!!)
        val byteArray: ByteArray? = inputStream?.readBytes()

        getUploadProfileImageUseCase(byteArray!!).onEach {
            when (it) {
                is Resource.Loading -> {
                    _loading = true
                }

                is Resource.Success -> {
                    _loading = false
                }

                is Resource.Error -> {
                    _loading = false
                    showToast(it.message.toString())
                    Timber.tag(TAG).e("Error: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}

