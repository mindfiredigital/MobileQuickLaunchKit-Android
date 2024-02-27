package com.foss.sample.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.foss.auth_presentation.screens.forgot_password.MQLKForgotPasswordScreen
import com.foss.auth_presentation.screens.login.LoginScreenViewModel
import com.foss.auth_presentation.screens.login.MQLKLoginScreen
import com.foss.auth_presentation.screens.otp_verification.MQLKOTPVerificationScreen
import com.foss.auth_presentation.screens.set_password.MQLKSetNewPasswordScreen
import com.foss.auth_presentation.screens.signup.MQLKSignUpScreen
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.core_ui.widgets.MQLKWebView
import com.foss.home.presentation.MQLKHomeScreen
import com.foss.onboarding_presentation.screen.MQLKSplashScreenUI
import com.foss.onboarding_presentation.screen.SplashScreenViewModel
import com.foss.settings.presentation.MQLKSettingsScreen
import com.foss.settings.presentation.change_password.MQLKChangePasswordScreen
import com.foss.settings.presentation.edit_profile.MQLKEditProfileScreen

/**
 * Defines the navigation structure for the app screens using Jetpack Compose Navigation.
 * This function sets up the navigation host and defines the screens and their respective routes.
 *
 * @param navController The NavController responsible for managing navigation within the graph.
 * @param startLocation The start destination location within the navigation graph.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleMQLKNavigationGraph(
    navController: NavHostController, startLocation: String, drawerState: DrawerState

) {
    NavHost(navController = navController, startDestination = startLocation) {
        // Define composable destinations and their corresponding routes
        composable(MQLKScreens.SplashScreen.route) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            MQLKSplashScreenUI(viewModel = viewModel, navigateTo = {
                navController.navigate(MQLKScreens.LoginScreen.route) {
                    popUpTo(MQLKScreens.SplashScreen.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(MQLKScreens.LoginScreen.route) {
            val viewModel: LoginScreenViewModel = hiltViewModel()
            MQLKLoginScreen(
                navController,
                viewModel = viewModel,
                onGoogleSignInButtonClickNavigate = {
                    navController.navigate(MQLKScreens.HomeScreen.route) {
                        popUpTo(MQLKScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onSignInButtonClickNavigate = {
                    navController.navigate(MQLKScreens.HomeScreen.route) {
                        popUpTo(MQLKScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onBioMetricsSignInButtonClickNavigate = {
                    navController.navigate(MQLKScreens.HomeScreen.route) {
                        popUpTo(MQLKScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(MQLKScreens.SignupScreen.route) {
            MQLKSignUpScreen(
                navController,
                onSignUpButtonClickNavigate = {
                    navController.navigate(MQLKScreens.HomeScreen.route) {
                        popUpTo(MQLKScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onGoogleSignUpButtonClickNavigate = {
                    navController.navigate(MQLKScreens.HomeScreen.route) {
                        popUpTo(MQLKScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(MQLKScreens.ForgotPasswordScreen.route) {
            MQLKForgotPasswordScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.OTPVerification.route) {
            MQLKOTPVerificationScreen(navController, {
                navController.navigate(it)

            })
        }

        composable(MQLKScreens.SetNewPassword.route) {
            MQLKSetNewPasswordScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.HomeScreen.route) {
            MQLKHomeScreen(drawerState)
        }

        composable(MQLKScreens.SettingScreen.route) {
            MQLKSettingsScreen(navController)
        }

        composable(MQLKScreens.EditProfileScreen.route) {
            MQLKEditProfileScreen(navController)
        }

        composable(MQLKScreens.ChangePasswordScreen.route) {
            MQLKChangePasswordScreen(navController)
        }

        composable(
            MQLKScreens.WebView.route, arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) {

            MQLKWebView(navController, it.arguments?.getString("url"))
        }
    }
}