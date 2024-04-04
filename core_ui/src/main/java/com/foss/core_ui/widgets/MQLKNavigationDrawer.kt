package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MQLKNavigationDrawerItem(
    name: String,
    isSelected: Boolean,
    icon: @Composable () -> Unit,
    onClickAction: () -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(bottom = 16.dp),
        label = {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700,
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        selected = isSelected,
        icon = icon,
        onClick = { onClickAction() },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,


            ),
        shape = RoundedCornerShape(6.dp)
    )
}