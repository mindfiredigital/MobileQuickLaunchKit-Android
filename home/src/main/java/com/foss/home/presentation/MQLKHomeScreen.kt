package com.foss.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.foss.core_ui.widgets.MQLKTopAppBarWrapperWithMenuIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MQLKHomeScreen(drawerState: DrawerState) {

    MQLKTopAppBarWrapperWithMenuIcon(drawerState = drawerState) {
        Box(
            modifier = it
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text("Home Screen")
        }
    }


}