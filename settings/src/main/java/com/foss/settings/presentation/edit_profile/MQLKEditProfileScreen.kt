package com.foss.settings.presentation.edit_profile

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.foss.core_ui.R
import com.foss.core_ui.rememberWindowSizeClass
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKElevatedButton
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.core_ui.widgets.MQLKOutlinedTextField
import com.foss.settings.presentation.widgets.MQLKProfileImageWrapper

@Composable
fun MQLKEditProfileScreen(navController: NavController) {

    val window = rememberWindowSizeClass();
    val context = LocalContext.current
    val viewModel: MQLKEditProfileScreenViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.setImage(uri) }
    )



    if (viewModel.loading) {
        MQLKLoadingDialog()
    }

    if (viewModel.toastMessage.isNotEmpty()) {
        Toast.makeText(context, viewModel.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.showToast("") // Reset the toast message after showing
    }

    MFMKAppBarWrapper(
        navController = navController,
        title = context.getString(R.string.editProfile)
    ) {

        Column(
            it
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MQLKProfileImageWrapper(
                    uri = viewModel.image,
                    width = window.width.size.dp,
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            MQLKOutlinedTextField(
                placeHolder = context.getString(R.string.email),
                onChange = { value ->
                    viewModel.onEmailValueChange(value)
                },
                value = viewModel.email,
                isError = viewModel.isEmailError,
                errorText = viewModel.emailErrorText
            ) {
                Icon(Icons.Default.Email, null)
            }
            Spacer(modifier = Modifier.height(16.dp))
            MQLKOutlinedTextField(
                placeHolder = context.getString(R.string.fullName),
                onChange = { value ->
                    viewModel.onFullNameValueChange(value)
                },
                value = viewModel.fullName,
                isError = viewModel.isFullNameError,
                errorText = viewModel.fullNameErrorText,
            ) {
                Icon(Icons.Default.Person, null)
            }
            Spacer(modifier = Modifier.height(16.dp))
            MQLKOutlinedTextField(
                placeHolder = context.getString(R.string.phoneNo),
                onChange = { value ->
                    viewModel.onPhoneNoValueChange(value)
                },
                value = viewModel.phoneNo,
                isError = viewModel.isPhoneNoError,
                errorText = viewModel.phoneNoErrorText,
            ) {
                Icon(Icons.Default.Phone, null)
            }
            Spacer(modifier = Modifier.weight(1.0f)) // fill height with spacer
            MQLKElevatedButton(context.getString(R.string.update)) {
                viewModel.updateUserProfile()
            }
        }

    }
}

