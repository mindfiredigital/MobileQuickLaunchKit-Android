package com.foss.auth_presentation.screens.otp_verification

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.foss.core_ui.R
import com.foss.core_ui.widgets.MQLKElevatedButton
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.core_ui.widgets.MQLKOtpTextField
import com.foss.core_ui.widgets.MQLKScreenTitle

/**
 * Composable representing the OTP verification screen UI.
 *
 * @param navigateTo Callback function to navigate to a specific destination.
 * @param viewModel ViewModel for managing otp verification screen logic and data.
 */
@Composable
fun MQLKOTPVerificationScreen(
    navController: NavController,
    navigateTo: (String) -> Unit,
    viewModel: OTPVerificationViewModel = hiltViewModel()
) {
    // Obtain focus manager
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    if (viewModel.loading) {
        MQLKLoadingDialog()
    }

    if (viewModel.toastMessage.isNotEmpty()) {
        Toast.makeText(context, viewModel.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.showToast("")
    }

    MFMKAppBarWrapper(navController = navController) {
        LazyColumn(
            modifier = it
                .fillMaxSize()
                .padding(horizontal = 21.dp),
        ) {

            // Title
            item {
                Spacer(modifier = Modifier.height(100.dp))

                MQLKScreenTitle(name = context.getString(R.string.otpVerification))

                Spacer(modifier = Modifier.height(20.dp))
            }

            // SubTitle
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = context.getString(R.string.otpVerificationSubtitle),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Left,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
            }

            // OTP TextField
            item {
                MQLKOtpTextField(
                    otpText = viewModel.code,
                    onOtpTextChange = { value, otpInputFilled ->
                        viewModel.onOtpChange(value)
                    },
                    isError = viewModel.isCodeError,
                    errorText = viewModel.codeErrorText
                )

                Spacer(modifier = Modifier.height(160.dp))
            }

            // verify OTP Button
            item {
                MQLKElevatedButton(name = context.getString(R.string.verifyOTP)) {
                    focusManager.clearFocus()
                    viewModel.onVerifyOTPBtnPressed(navigateTo)
                }
            }
        }
    }

}


@Preview(showBackground = true, device = Devices.PHONE, showSystemUi = false)
@Composable
fun PreviewMFMCOTPVerification() {
    val navController = rememberNavController()
    MQLKOTPVerificationScreen(navController, {})
}
