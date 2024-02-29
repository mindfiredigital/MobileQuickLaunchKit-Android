package com.foss.onboarding_presentation.screen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {
    fun checkIfLoggedIn(callBack: () -> Unit) {
        callBack()
    }
}

