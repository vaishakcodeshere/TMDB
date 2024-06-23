package com.tmdbapp.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tmdbapp.tmdb.ui.theme.SearchPlaceHolder
import com.tmdbapp.tmdb.ui.theme.SearchStroke
import com.tmdbapp.tmdb.ui.theme.TMDBTheme
import com.tmdbapp.tmdb.ui.theme.poppinsFamily

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        HomeTopBar()
                    }) { innerPadding ->
                    HomeView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeTopBar() {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 42.dp)) {
        Text(
            text = "What do you want to watch?",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 18.sp
        )
        var searchTextState by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 30.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SearchStroke,
                unfocusedContainerColor = SearchStroke,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
                ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = SearchPlaceHolder
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search"
                )
            },
            value = searchTextState,
            onValueChange = { value -> searchTextState = value }
        )
    }
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {

}