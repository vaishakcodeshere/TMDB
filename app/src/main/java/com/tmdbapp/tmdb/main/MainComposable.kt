package com.tmdbapp.tmdb.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tmdbapp.tmdb.utils.NoRippleInteractionSource
import com.tmdbapp.tmdb.home.HomeView
import com.tmdbapp.tmdb.ui.theme.BottomBarSelectedItem
import com.tmdbapp.tmdb.ui.theme.BottomBarUnSelectedItem


@Composable
fun BottomBar(
    navHostController: NavHostController, modifier: Modifier = Modifier
) {
    val navItems = listOf(
        BottomBarData.Home, BottomBarData.Search, BottomBarData.WatchList
    )

    NavigationBar(
        modifier = modifier, containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { screen ->
            val textColor =
                if (currentRoute == screen.route) BottomBarSelectedItem else BottomBarUnSelectedItem
            NavigationBarItem(
                interactionSource = NoRippleInteractionSource,
                selected = currentRoute == screen.route,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "${screen.route} icon"
                    )
                },
                label = {
                    Text(text = screen.label, fontSize = 12.sp, color = textColor)
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color.Transparent,
                    selectedIconColor = BottomBarSelectedItem,
                    unselectedIconColor = BottomBarUnSelectedItem
                )
            )
        }
    }
}

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = BottomBarData.Home.route) {
        composable(BottomBarData.Home.route) {
            HomeView()
        }
        composable(BottomBarData.Search.route) {
            HomeView()
        }
        composable(BottomBarData.WatchList.route) {
            HomeView()
        }
    }
}