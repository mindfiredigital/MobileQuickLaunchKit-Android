package com.foss.settings.presentation.change_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.foss.core_ui.R
import com.foss.core_ui.widgets.MQLKElevatedButton
import com.foss.core_ui.widgets.MFMKAppBarWrapper
import com.foss.core_ui.widgets.MQLKOutlinedTextField

@Composable
fun MQLKChangePasswordScreen(navController: NavController) {
    val context = LocalContext.current

    MFMKAppBarWrapper(
        navController = navController,
        title = context.getString(R.string.changePassword)
    ) {

        var ll1 by remember {
            mutableStateOf("")
        }
        var ll2 by remember {
            mutableStateOf("")
        }
        LazyColumn(
            modifier = it
                .fillMaxSize()
                .padding(horizontal = 21.dp),
        ) {
            item { Spacer(modifier = Modifier.height(32.dp)) }
            // New Password TextField
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.newPassword),
                    onChange = {
                        ll1 = it
                    },
                    value = ll1,
                    isError = false,
                    errorText = "viewModel.passwordErrorText",

                    ) {
                    Icon(Icons.Default.Lock, null)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Confirm New Password TextField
            item {
                MQLKOutlinedTextField(
                    placeHolder = context.getString(R.string.confirmNewPassword),
                    onChange = {
                        ll2 = it

                    },
                    value = ll2,
                    isError = false,
                    errorText = "viewModel.passwordErrorText",

                    ) {
                    Icon(Icons.Default.Lock, null)
                }
                Spacer(modifier = Modifier.height(160.dp))
            }

            // Submit password Button
            item {
                MQLKElevatedButton(name = context.getString(R.string.submitPassword)) {
                    navController.popBackStack()
                }
            }
        }
    }


}