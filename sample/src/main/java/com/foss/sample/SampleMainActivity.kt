package com.foss.sample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import com.foss.core_ui.widgets.MQLKModelNavigationDrawerWrapper
import com.foss.sample.navigation.SampleMQLKNavigationGraph
import com.foss.utility.MQLKUtilities
import kotlinx.coroutines.launch

@Composable

fun SampleModule() {
    val window = rememberWindowSizeClass()

    MQLKTheme(
        windowSizeClass = window,
    ) {
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(content = {})
                    IconButton(modifier = Modifier, onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(Icons.Default.Close, null)
                    }
                }
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { MQLKUtilities.sharePlainText(context = context) },
                    icon = {
                        Icon(Icons.Default.Phone, null)
                    },
                )
            },
            {
                NavigationDrawerItem(label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ })
            },
            {
                NavigationDrawerItem(label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ })
            },
        ),
    ) {
        Scaffold(modifier = Modifier.clickable(indication = null,
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







