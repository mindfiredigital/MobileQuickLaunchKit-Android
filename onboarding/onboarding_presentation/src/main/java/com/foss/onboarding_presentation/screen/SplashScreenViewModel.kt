package com.foss.onboarding_presentation.screen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.shared.domain.use_cases.GetUserDataFromDataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val getUserDataFromDataStoreUseCase: GetUserDataFromDataStoreUseCase) :
    ViewModel() {

    suspend fun checkIfLoggedIn(navController: NavController) {
//        val userData = getUserDataFromDataStoreUseCase()
//
//        if (userData?.token != null && userData.token!!.isNotBlank()) {
//            navController.navigate(MQLKScreens.HomeScreen.route)
//        } else {
//            navController.navigate(MQLKScreens.LoginScreen.route)
//        }
        navController.navigate(MQLKScreens.LoginScreen.route) {
            popUpTo(MQLKScreens.SplashScreen.route) {
                inclusive = true
            }
        }
    }
}

