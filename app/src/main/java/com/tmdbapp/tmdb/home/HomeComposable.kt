package com.tmdbapp.tmdb.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tmdbapp.tmdb.R
import com.tmdbapp.tmdb.ui.theme.AppBackground
import com.tmdbapp.tmdb.ui.theme.SearchPlaceHolder
import com.tmdbapp.tmdb.ui.theme.SearchStroke
import com.tmdbapp.tmdb.ui.theme.poppinsFamily
import com.tmdbapp.tmdb.utils.NoRippleInteractionSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun HomeView(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.
    padding(start = 24.dp, end = 24.dp, top = 42.dp)) {
        Text(
            text = "What do you want to watch?",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 18.sp
        )
        var searchTextState by remember { mutableStateOf("") }

        TextField(modifier = Modifier
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
                    painter = painterResource(id = R.drawable.search), contentDescription = "search"
                )
            },
            value = searchTextState,
            onValueChange = { value -> searchTextState = value })

        TopCollection()
        TabLayout()
    }
}

@Preview
@Composable
fun TopCollection() {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        LazyRow {
            items(count = 10) {
                ImageCardView()
            }
        }
    }
}

@Preview
@Composable
fun ImageCardView() {
    Card(
        modifier = Modifier
            .padding(end = 15.dp)
            .width(145.dp)
            .height(210.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dummy_image),
            contentDescription = "topImage",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabLayout() {
    val tabs = listOf("Now Playing", "Upcoming", "Top Rated", "Popular")
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    Column(modifier = Modifier.padding(top = 24.dp)) {
        val scope = rememberCoroutineScope()

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = AppBackground,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.White
                )
            },
            edgePadding = 0.dp,
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    interactionSource = NoRippleInteractionSource
                )
            }

        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { index ->
            PagerContent()
        }
    }
}

@Preview
@Composable
fun PagerContent() {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 100.dp)) {
        items(count = 10) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(145.dp)
                    .background(AppBackground)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_image),
                    contentDescription = "topImage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(AppBackground)
                )
            }
        }
    }
}