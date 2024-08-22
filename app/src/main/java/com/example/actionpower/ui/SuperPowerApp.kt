package com.example.actionpower.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.actionpower.navigation.AppDestination
import com.example.actionpower.navigation.SuperPowerNavHost
import com.example.actionpower.ui.theme.Purple40
import com.example.actionpower.ui.theme.Purple80
import com.example.search.SEARCH_ROUTE
import com.example.search.navigateToSearch
import com.example.video.VIDEO_ROUTE
import com.example.video.navigateToVideo

@Composable
fun SuperPowerApp() {
    val navHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val currentRoute = navHostController
                    .currentBackStackEntryAsState().value?.destination?.route
                NavigationBarItem(
                    selected = currentRoute == SEARCH_ROUTE,
                    onClick = {
                        navigateToAppDestination(
                            navHostController,
                            AppDestination.SEARCH
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "NavigationBar Search"
                        )
                    },
                    label = {
                        Text(text = "Search")
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Purple40,
                        //unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
                        selectedTextColor = Purple40,
                        //unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
                        indicatorColor = Purple80,
                    )
                )
                NavigationBarItem(
                    selected = currentRoute == VIDEO_ROUTE,
                    onClick = {
                        navigateToAppDestination(
                            navHostController,
                            AppDestination.VIDEO
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.OndemandVideo,
                            contentDescription = "NavigationBar Video"
                        )
                    },
                    label = {
                        Text(text = "Video")
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Purple40,
                        //unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
                        selectedTextColor = Purple40,
                        //unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
                        indicatorColor = Purple80,
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)) {
            SuperPowerNavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navHostController,
            )
        }
    }
}

fun navigateToAppDestination(
    navController: NavController,
    appDestination: AppDestination
) {
    trace("Navigation: ${appDestination.name}") {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (appDestination) {
            AppDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
            AppDestination.VIDEO -> navController.navigateToVideo(topLevelNavOptions)
        }
    }
}