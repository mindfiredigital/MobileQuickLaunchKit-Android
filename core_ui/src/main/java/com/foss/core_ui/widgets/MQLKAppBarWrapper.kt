package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MFMKAppBarWrapper(
    modifier: Modifier = Modifier,
    title: String? = null,
    navController: NavController,
    showBackButton: Boolean = true,
    widget: @Composable (modifier: Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = {
                    if (title != null) {
                        MQLKScreenTitle(title)
                    }
                },
                navigationIcon = {
                    if (showBackButton) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowLeft,
                                null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }


                }
            )
        }
    ) {
        widget(modifier.padding(it))
    }
}