package com.foss.core_ui.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MQLKModelNavigationDrawerWrapper(
    drawerState: DrawerState, items: List<@Composable () -> Unit>, context: @Composable () -> Unit
) {
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = false, drawerContent = {
        ModalDrawerSheet(
            drawerContainerColor = Color.White,
            drawerTonalElevation = 0.dp,
        ) {
            LazyColumn {
                items.map {
                    item {
                        it()
                    }
                }
            }
        }
    }) {
        context()
    }
}
