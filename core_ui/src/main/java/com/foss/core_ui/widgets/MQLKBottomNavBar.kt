package com.foss.core_ui.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.foss.core_ui.navigation.MQLKNavItem

@Composable
fun MQLKBottomNavBar(
    items: List<MQLKNavItem>,
    navController: NavController,
    onClick: (MQLKNavItem) -> Unit,
) {

    val backStack = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background, tonalElevation = 0.dp
    ) {
        items.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        it.icon,
                        null
                    )
                },
                label = {
                    Text(it.label)
                },
                selected = it.route == backStack.value?.destination?.route,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}


