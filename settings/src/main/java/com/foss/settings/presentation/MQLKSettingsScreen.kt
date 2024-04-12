package com.foss.settings.presentation

import SegmentText
import SegmentedControl
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.foss.core.utils.AppConstant
import com.foss.core_ui.MQLKTheme
import com.foss.core_ui.R
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.core_ui.rememberWindowSizeClass
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKLoadingDialog
import com.foss.core_ui.widgets.MQLKSettingScreenUserCard
import com.foss.core_ui.widgets.MQLKSettingsMenuCard
import com.foss.settings.presentation.widgets.MQLKSettingPageSubTitle

@Composable
fun MQLKSettingsScreen(navController: NavController) {
    val viewModel: MQLKSettingsScreenViewModel = hiltViewModel()
    val context = LocalContext.current

//     Loading widget
    if (viewModel.loading) {
        MQLKLoadingDialog()
    }
    MFMKAppBarWrapper(
        navController = navController,
        title = context.getString(R.string.settings),
        showBackButton = false,
        backgroundColor = Color(0xffF5F5F5)


    ) {

        LazyColumn(
            modifier = it
                .padding(horizontal = 20.dp)

        ) {

            // SubTitle
            item {
                MQLKSettingPageSubTitle(
                    context.getString(R.string.account),
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        MQLKSettingScreenUserCard(onClick = {
                            navController.navigate(MQLKScreens.EditProfileScreen.route)

                        })
                        MQLKSettingsMenuCard(text = context.getString(R.string.changePassword),
                            onClick = {
                                navController.navigate(MQLKScreens.ChangePasswordScreen.route)
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, null)
                            }
                        )
                        MQLKSettingsMenuCard(
                            text = context.getString(R.string.logout),
                            showDivider = false,
                            onClick = {
                                viewModel.logout(context, navController)

                            },
                            leadingIcon = {
                                Icon(painterResource(id = R.drawable.baseline_logout), null)
                            }
                        )

                    }

                }
            }


            item {
                MQLKSettingPageSubTitle(
                    "App"
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {

                        val threeSegments = remember { listOf("Auto", "Dark", "Light") }
                        var selectedThreeSegment by remember { mutableStateOf(threeSegments.first()) }
                        Row {
                            Icon(painterResource(id = R.drawable.baseline_contrast), null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Theme",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W600
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        SegmentedControl(
                            threeSegments,
                            selectedThreeSegment,
//                            modifier = Modifier.padding(horizontal = 10.dp),
                            onSegmentSelected = { selectedThreeSegment = it }
                        ) {
                            SegmentText(it)
                        }

                    }

                }
            }



            item {
                MQLKSettingPageSubTitle(
                    context.getString(R.string.others)
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        MQLKSettingsMenuCard(text = context.getString(R.string.privacy),
                            onClick = {
                                navController.navigate(
                                    MQLKScreens.WebView.passUrlTitle(
                                        url = AppConstant.Endpoints.PRIVACY_URL,
                                        title = context.getString(R.string.privacy)
                                    )
                                )
                            },
                            leadingIcon = {
                                Icon(painterResource(id = R.drawable.baseline_verified_user), null)
                            }
                        )
                        MQLKSettingsMenuCard(
                            text = context.getString(R.string.help),
                            onClick = {
                                navController.navigate(
                                    MQLKScreens.WebView.passUrlTitle(
                                        url = AppConstant.Endpoints.HELP_URL,
                                        title = context.getString(R.string.help)
                                    )
                                )
                            },
                            leadingIcon = {
                                Icon(painterResource(id = R.drawable.baseline_help), null)
                            }
                        )
                        MQLKSettingsMenuCard(
                            text = context.getString(R.string.aboutUs),
                            showDivider = false,
                            onClick = {
                                MQLKScreens.WebView.passUrlTitle(
                                    url = AppConstant.Endpoints.ABOUT_US_URL,
                                    title = context.getString(R.string.aboutUs)
                                )
                            },
                            leadingIcon = {
                                Icon(painterResource(id = R.drawable.baseline_error_outline), null)
                            }
                        )

                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewSettingScreen() {
    MQLKTheme(windowSizeClass = rememberWindowSizeClass(), byPassConfig = true) {
        MQLKSettingsScreen(rememberNavController())

    }
}

