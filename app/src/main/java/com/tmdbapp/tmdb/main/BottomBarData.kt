package com.tmdbapp.tmdb.main

import com.tmdbapp.tmdb.R

sealed class BottomBarData(val label: String, val icon: Int, val route: String) {
    data object Home : BottomBarData(
        label = "Home", icon = R.drawable.home, route = "home"
    )

    data object Search : BottomBarData(
        label = "Search", icon = R.drawable.search, route = "search"
    )

    data object WatchList : BottomBarData(
        label = "WatchList", icon = R.drawable.wishlist, route = "watchlist"
    )
}