package com.foss.sample

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.foss.core_ui.MQLKTheme
import com.foss.core_ui.navigation.MQLKNavItem
import com.foss.core_ui.navigation.MQLKScreens
import com.foss.core_ui.rememberWindowSizeClass
import com.foss.core_ui.widgets.MQLKBottomNavBar
import com.foss.core_ui.widgets.MQLKDrawerTopSection
import com.foss.core_ui.widgets.MQLKModelNavigationDrawerWrapper
import com.foss.core_ui.widgets.MQLKNavigationDrawerItem
import com.foss.sample.navigation.SampleMQLKNavigationGraph
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable

fun SampleModule() {
    val window = rememberWindowSizeClass()

    MQLKTheme(
        windowSizeClass = window,
    ) {
        val postNotificationPermission =
            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
        LaunchedEffect(key1 = true) {
            if (!postNotificationPermission.status.isGranted) {
                postNotificationPermission.launchPermissionRequest()
            }
        }

        MyApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val scope = rememberCoroutineScope()
    val navItems = listOf(
        MQLKNavItem(
            label = "Home", route = MQLKScreens.HomeScreen.route, icon = Icons.Default.Home
        ),
        MQLKNavItem(
            label = "Settings",
            route = MQLKScreens.SettingScreen.route,
            icon = Icons.Default.Settings
        ),
    )
    val navController = rememberNavController()
    val focusManager = LocalFocusManager.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current
    // Drawer wrapper
    //
    MQLKModelNavigationDrawerWrapper(
        drawerState,
        items = listOf(
            {
                MQLKDrawerTopSection(
                    onCloseButtonClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    imageUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cGVyc29ufGVufDB8fDB8fHww"
                )
            },
            {
                MQLKNavigationDrawerItem(name = "Home", isSelected = true, icon = {
                    Icon(Icons.Default.Home, null)

                }, onClickAction = {})

            },
            {
                MQLKNavigationDrawerItem(name = "Settings", isSelected = false, icon = {
                    Icon(Icons.Default.Settings, null)

                }, onClickAction = {})
            },
            {
                MQLKNavigationDrawerItem(name = "Settings", isSelected = false, icon = {
                    Icon(Icons.Default.Settings, null)

                }, onClickAction = {})
            },
        ),
    ) {
        Scaffold(modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            focusManager.clearFocus()
        }, containerColor = MaterialTheme.colorScheme.background, bottomBar = {
            val items = navItems.map {
                it.route
            }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.hierarchy?.first()?.route
            //responsible for showing bottom navigation bar
            if (currentRoute !in items) return@Scaffold
            MQLKBottomNavBar(
                items = navItems,
                navController = navController,
            ) {
                navController.navigate(it.route) {
                    popUpTo(MQLKScreens.HomeScreen.route) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }) { it ->
            SampleMQLKNavigationGraph(navController, MQLKScreens.SplashScreen.route, drawerState)
        }
    }
}







