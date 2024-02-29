package com.foss.auth_presentation.screens.login

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.foss.auth_presentation.screens.login.widgets.MQLKLoginScreenForgotPasswordButton
import com.foss.auth_presentation.screens.signup.GoogleSignInActivityResultContract
import com.foss.core_ui.R
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.core_ui.widgets.MQLKElevatedButton
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.core_ui.widgets.MQLKLogo
import com.foss.core_ui.widgets.MQLKOutlinedTextField
import com.foss.core_ui.widgets.MQLKScreenTitle
import com.foss.core_ui.widgets.MQLKSocialCard
import com.foss.core_ui.widgets.MQLKTouchIdCard

/**
 * Composable representing the login screen UI.
 *
 * @param navigateTo Callback function to navigate to a specific destination.
 * @param viewModel ViewModel for managing login screen logic and data.
 */
@Composable
fun MQLKLoginScreen(
    navController: NavController,
    onSignInButtonClickNavigate: () -> Unit,
    onGoogleSignInButtonClickNavigate: () -> Unit,
    onBioMetricsSignInButtonClickNavigate: () -> Unit,
    viewModel: LoginScreenViewModel,
) {

    // Obtain focus manager
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current as FragmentActivity
    val activity = (LocalContext.current as? Activity)

    // Google sign in
    val googleSignInLauncher = rememberLauncherForActivityResult(
        GoogleSignInActivityResultContract()
    ) { result ->
        viewModel.onGoogleLoginPressed(
            result,
            onSignInSuccessCallBack = {
                onGoogleSignInButtonClickNavigate()
            },
        )
    }
    LaunchedEffect(true) {
        viewModel.ll(context)
    }

    if (viewModel._loading) {
        MQLKLoadingDialog()
    }

    if (viewModel.toastMessage.isNotEmpty()) {
        Toast.makeText(context, viewModel.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.showToast("") // Reset the toast message after showing
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 21.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Image
        item {
            MQLKLogo(100.dp)
        }
        // Title
        item {
            Column {
                MQLKScreenTitle(name = context.getString(R.string.signIn))
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Email/Username TextField
        item {
            Column {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.emailOrUserName),
                    onChange = {
                        viewModel.onEmailValueChange(it)
                    },
                    value = viewModel.email,
                    isError = viewModel.isEmailError,
                    errorText = viewModel.emailErrorText,

                    ) {
                    Icon(Icons.Default.Person, null)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
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
        }

        // "Forgot Password" Text
        item {
            Column {
                MQLKLoginScreenForgotPasswordButton {
                    navController.navigate(MQLKScreens.ForgotPasswordScreen.route)
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        // Sign-In Button
        item {
            Column {
                MQLKElevatedButton(name = context.getString(R.string.signIn)) {
                    focusManager.clearFocus()
                    viewModel.onSignInButtonPressed(
                        callback = {
                            onSignInButtonClickNavigate()
                        });
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Other sign in text
        item {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = context.getString(R.string.orSignInWith),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal, textAlign = TextAlign.Center
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // Other Sign-In Options (e.g., Google, Meta)
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    MQLKSocialCard(painter = painterResource(id = R.drawable.google_icon)) {
                        googleSignInLauncher.launch(Unit)
                    }
                    Spacer(Modifier.width(20.dp))
                    MQLKSocialCard(painter = painterResource(id = R.drawable.meta_icon)) {}
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        if (viewModel.canShowBioMetrics) {
            item {
                MQLKTouchIdCard {
                    viewModel.doBioMetricsAuth(context, callback = {
                        onBioMetricsSignInButtonClickNavigate()
                    })
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }


        // Text for Sign-Up
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.wrapContentWidth(),
                    text = context.getString(R.string.DoNotHaveAccount),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                    ),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = " " + context.getString(R.string.signUp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(MQLKScreens.SignupScreen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

        }


    }

}

//@Preview(showBackground = true, device = Devices.PHONE, showSystemUi = false)
//@Composable
//fun PreviewMFMCLoginScreen() {
//    val navController = rememberNavController()
//    MQLKLoginScreen(navController, {})
//}
