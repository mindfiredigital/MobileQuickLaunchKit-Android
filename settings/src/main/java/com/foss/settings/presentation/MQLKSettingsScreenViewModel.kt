package com.foss.settings.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.foss.core.use_cases.GetClearDataStoreUseCase
import com.foss.core.utils.AppConstant
import com.foss.core_ui.navigation.MQLKScreens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MQLKSettingsScreenViewModel @Inject constructor(private val getClearDataStoreUseCase: GetClearDataStoreUseCase) :
    ViewModel(
    ) {
    private var _loading: Boolean by mutableStateOf(false)
    val loading: Boolean
        get() = _loading

    fun logout(context: Context, navController: NavController) {
        viewModelScope.launch {
            val firebaseAuth = FirebaseAuth.getInstance()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(AppConstant.Endpoints.Google_WEB_CLIENT_ID)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)

            firebaseAuth.signOut()
            googleSignInClient.signOut()
            navController.navigate(MQLKScreens.SplashScreen.route) {
                popUpTo(MQLKScreens.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

    }
}