package com.foss.auth_presentation.screens.signup

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.foss.core_ui.R
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKElevatedButton
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.core_ui.widgets.MQLKOutlinedTextField
import com.foss.core_ui.widgets.MQLKScreenTitle
import com.foss.core_ui.widgets.MQLKSocialCard


/**
 * Composable representing the sign up screen UI.
 *
 * @param navigateTo Callback function to navigate to a specific destination.
 * @param viewModel ViewModel for managing sign up screen logic and data.
 */
@Composable
fun MQLKSignUpScreen(
    navController: NavController,
    navigateTo: (String) -> Unit,
    viewModel: SignUpScreenViewModel = hiltViewModel()
) {
    // Obtain focus manager
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // Google sign in
    val googleSignInLauncher = rememberLauncherForActivityResult(
        GoogleSignInActivityResultContract()
    ) { result ->
        viewModel.onGoogleSignInResult(result, navController)
    }

    // Loading widget
    if (viewModel.loading) {
        MQLKLoadingDialog()
    }

    // Toast
    if (viewModel.toastMessage.isNotEmpty()) {
        Toast.makeText(context, viewModel.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.showToast("") // Reset the toast message after showing
    }

    MFMKAppBarWrapper(navController = navController) { it ->
        LazyColumn(
            modifier = it
                .fillMaxSize()
                .padding(horizontal = 21.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // Title
            item {
                Spacer(modifier = Modifier.height(20.dp))
                MQLKScreenTitle(name = context.getString(R.string.signUp))
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Full name
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.fullName),
                    onChange = {
                        viewModel.onFullNameValueChange(it)
                    },
                    value = viewModel.fullName,
                    isError = viewModel.isFullNameError,
                    errorText = viewModel.fullNameErrorText,

                    ) {
                    Icon(Icons.Default.Person, null)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Email
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.email),
                    onChange = {
                        viewModel.onEmailValueChange(it)
                    },
                    value = viewModel.email,
                    isError = viewModel.isEmailError,
                    errorText = viewModel.emailErrorText,

                    ) {
                    Icon(Icons.Default.Email, null)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Password TextField
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.password),
                    onChange = {
                        viewModel.onPasswordValueChange(it)
                    },
                    value = viewModel.password,
                    isError = viewModel.isPasswordError,
                    errorText = viewModel.passwordErrorText,
                    isMask = true,
                    passwordVisibilityState = false,
                ) {
                    Icon(Icons.Default.Lock, null)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Confirm password TextField
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.confirmPassword),
                    onChange = {
                        viewModel.onConfirmPasswordValueChange(it)
                    },
                    value = viewModel.confirmPassword,
                    isError = viewModel.isConfirmPasswordError,
                    errorText = viewModel.confirmPasswordErrorText,
                    isMask = true,
                    passwordVisibilityState = false,
                ) {
                    Icon(Icons.Default.Lock, null)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }

            // Sign-Up Button
            item {
                MQLKElevatedButton(name = context.getString(R.string.signUp)) {
                    focusManager.clearFocus()
                    viewModel.onSignUpButtonPressed(navController);
                }
                Spacer(modifier = Modifier.height(60.dp))
            }

            // Signup with other
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = context.getString(R.string.orSignUpWith),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal, textAlign = TextAlign.Center
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Social login
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    MQLKSocialCard(painter = painterResource(id = R.drawable.google_icon)) {
                        googleSignInLauncher.launch(Unit)
                    }
                    Spacer(Modifier.width(20.dp))
                    MQLKSocialCard(painter = painterResource(id = R.drawable.meta_icon)) {}
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

            // Text for Already have account
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = context.getString(R.string.alreadyHaveAccount),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(text = context.getString(R.string.signIn),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                }
                Spacer(modifier = Modifier.height(30.dp))

            }

        }
    }

}


@Preview(showBackground = true, device = Devices.PHONE, showSystemUi = false)
@Composable
fun PreviewMFMCSignUpScreen() {
    val navController = rememberNavController()
    MQLKSignUpScreen(navController, {})
}


