package com.foss.onboarding_presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 13-12-2023
 */

@Composable
fun MQLKSplashScreenUI(navController: NavController, viewModel: SplashScreenViewModel) {
    val context = LocalContext.current
    LaunchedEffect(context) {
        delay(2000)
        viewModel.checkIfLoggedIn(navController)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = com.foss.core_ui.R.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}