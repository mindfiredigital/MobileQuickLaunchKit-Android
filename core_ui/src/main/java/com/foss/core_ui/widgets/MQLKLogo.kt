package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.foss.core_ui.R

@Composable
fun MQLKLogo(height:Dp){
    Icon(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}