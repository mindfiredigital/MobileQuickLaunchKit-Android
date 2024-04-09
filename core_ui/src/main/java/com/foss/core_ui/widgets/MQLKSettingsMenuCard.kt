package com.foss.core_ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MQLKSettingsMenuCard(
    text: String, showDivider: Boolean = true,
    onClick: () -> Unit, leadingIcon: @Composable () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clickable {
                    onClick()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                leadingIcon()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W600
                    )
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
            }
        }
        if (showDivider) {
            HorizontalDivider()
        }
    }
}