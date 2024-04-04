package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
            Column(Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.padding(24.dp)) {
                    items.map {
                        item {
                            it()
                        }
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                Text(
                    "Version: 1.0.0",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                )

            }


        }
    }) {
        context()
    }
}
