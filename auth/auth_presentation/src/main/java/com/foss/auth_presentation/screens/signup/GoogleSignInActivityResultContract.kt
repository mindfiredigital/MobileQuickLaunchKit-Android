package com.foss.auth_presentation.screens.signup

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.foss.core.utils.AppConstant
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleSignInActivityResultContract : ActivityResultContract<Unit, GoogleSignInAccount?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(AppConstant.Endpoints.Google_WEB_CLIENT_ID)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        return googleSignInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): GoogleSignInAccount? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        return if (task.isSuccessful) {
            task.result
        } else {
            null
        }
    }
}
