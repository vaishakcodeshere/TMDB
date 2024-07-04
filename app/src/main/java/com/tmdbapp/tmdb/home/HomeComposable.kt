package com.tmdbapp.tmdb.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.semantics.Role.Companion.Button
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(modifier: Modifier = Modifier) {

    val tabs = listOf("Now Playing", "Upcoming", "Top Rated", "Popular")
    val pagerState: PagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        item {
            Column {
                TextSearchComposable()
                TopCollection()
            }
        }

        stickyHeader {
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
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        interactionSource = NoRippleInteractionSource
                    )
                }

            }
        }

        item {
            HorizontalPager(
                state = pagerState
            ) { index ->
                PagerContent()
            }
        }
    }
}

@Preview
@Composable
fun TextSearchComposable() {
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

@Preview
@Composable
fun PagerContent() {
    val approxColum = 20 / 4
    val heightInt: Int = 145 * approxColum
    LazyVerticalGrid(
        modifier = Modifier.height(heightInt.dp),
        columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
        items(count = 20) {
            Card(
                modifier = Modifier
                    .height(180.dp)
                    .width(100.dp)
                    .background(Color.Transparent)
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White)
                ) {

                }
            }
        }
    }
}