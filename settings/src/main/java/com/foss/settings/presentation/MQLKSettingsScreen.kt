package com.foss.settings.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.foss.core.utils.AppConstant
import com.foss.core_ui.R
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.settings.presentation.widgets.MQLKSettingPageItem
import com.foss.settings.presentation.widgets.MQLKSettingPageSubTitle

@Composable
fun MQLKSettingsScreen(navController: NavController) {
    val viewModel: MQLKSettingsScreenViewModel = hiltViewModel()
    val context = LocalContext.current

    // Loading widget
    if (viewModel.loading) {
        MQLKLoadingDialog()
    }
    MFMKAppBarWrapper(
        navController = navController,
        title = context.getString(R.string.settings),
        showBackButton = false
    ) {
        LazyColumn(
            modifier = it.padding(horizontal = 20.dp)
        ) {

            // SubTitle
            item {
                MQLKSettingPageSubTitle(
                    context.getString(R.string.account), resource = R.drawable.settings_account_box
                )
            }

            // Setting options
            //
            item {
                MQLKSettingPageItem(context.getString(R.string.editProfile)) {
                    navController.navigate(MQLKScreens.EditProfileScreen.route)
                }
            }

            item {
                MQLKSettingPageItem(context.getString(R.string.changePassword)) {
                    navController.navigate(MQLKScreens.ChangePasswordScreen.route)
                }
            }

            item {
                MQLKSettingPageItem(context.getString(R.string.privacy)) {
                    navController.navigate(MQLKScreens.WebView.passUrl(url = AppConstant.Endpoints.PRIVACY_URL))
                }
            }

            item {
                MQLKSettingPageItem(context.getString(R.string.logout)) {
                    viewModel.logout(context, navController)
                }
            }

            item {
                MQLKSettingPageSubTitle(
                    context.getString(R.string.others), resource = R.drawable.settings_account_box
                )
            }

            item {
                MQLKSettingPageItem(context.getString(R.string.help)) {
                    navController.navigate(MQLKScreens.WebView.passUrl(url = AppConstant.Endpoints.HELP_URL))

                }
            }

            item {
                MQLKSettingPageItem(context.getString(R.string.aboutUs)) {
                    navController.navigate(MQLKScreens.WebView.passUrl(url = AppConstant.Endpoints.ABOUT_US_URL))
                }
            }
        }
    }

}

