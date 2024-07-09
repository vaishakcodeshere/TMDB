package com.tmdbapp.tmdb.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdbapp.tmdb.home.PagerContent
import com.tmdbapp.tmdb.home.TextSearchComposable

@Composable
fun SearchView(modifier : Modifier= Modifier){
    Column {
        TextSearchComposable(searchText = "What do you want to Search?")
        Spacer(modifier = Modifier.padding(top = 20.dp))
        PagerContent()
    }
}