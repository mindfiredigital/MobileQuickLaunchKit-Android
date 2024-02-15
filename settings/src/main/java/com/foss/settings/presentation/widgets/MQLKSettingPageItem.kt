package com.foss.settings.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MQLKSettingPageItem(name: String, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(9.dp))
    }
}