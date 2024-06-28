package com.tmdbapp.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tmdbapp.tmdb.main.BottomBar
import com.tmdbapp.tmdb.main.NavigationGraph
import com.tmdbapp.tmdb.ui.theme.TMDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBTheme {
                val navController: NavHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomBar(
                        navHostController = navController
                    )
                }) { innerPadding ->
                    //todo add dummy text data
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationGraph(navHostController = navController)
                    }
                }
            }
        }
    }
}