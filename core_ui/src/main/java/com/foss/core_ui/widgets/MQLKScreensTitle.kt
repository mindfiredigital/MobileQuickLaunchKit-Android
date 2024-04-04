package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MQLKScreenTitle(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            name,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
    }
}