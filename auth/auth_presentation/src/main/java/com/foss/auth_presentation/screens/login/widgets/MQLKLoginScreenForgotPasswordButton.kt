package com.foss.auth_presentation.screens.login.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.foss.core_ui.R

@Composable
fun MQLKLoginScreenForgotPasswordButton(onClick: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = context.getString(R.string.forgotPassword),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}