package com.foss.core_ui.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class MQLKScreens(
    val route: String,

    ) {
    object SplashScreen : MQLKScreens(
        "splash_screen",
    )

    object LoginScreen : MQLKScreens(
        "login_screen",

        )

    object SignupScreen : MQLKScreens(
        "signup_screen",
    )

    object ForgotPasswordScreen : MQLKScreens(
        "forgot_password_screen",
    )

    object OTPVerification : MQLKScreens(
        "otp_verification",
    )

    object SetNewPassword : MQLKScreens(
        "set_new_password",
    )

    object HomeScreen : MQLKScreens(
        "home_screen",

        )

    object SettingScreen : MQLKScreens(
        "setting_screen",
    )

    object EditProfileScreen : MQLKScreens(
        "editprofile_screen",
    )

    object ChangePasswordScreen : MQLKScreens(
        "changepassowrd_screen",
    )

    object WebView : MQLKScreens(
        "webview_screen/{url}/{title}",
    ) {
        fun passUrlTitle(url: String,title:String): String {
            val encodedUrl =
                URLEncoder.encode(
                    url,
                    StandardCharsets.UTF_8.toString()
                )
            val encodedTitle =
                URLEncoder.encode(
                    title,
                    StandardCharsets.UTF_8.toString()
                )
            return this.route.replace(oldValue = "{url}", newValue = encodedUrl).replace("{title}", encodedTitle)
        }
    }


}