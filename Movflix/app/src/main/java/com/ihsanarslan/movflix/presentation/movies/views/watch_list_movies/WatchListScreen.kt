package com.ihsanarslan.movflix.presentation.movies.views.watch_list_movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ihsanarslan.movflix.presentation.movies.views.BottomMenuWatchList
import com.ihsanarslan.movflix.presentation.Screen
import com.ihsanarslan.movflix.presentation.movies.view_model.watch_list.WatchListViewModel
import com.ihsanarslan.movflix.util.Constants


@Composable
fun WatchListScreen(
    navController: NavController,
    viewModel : WatchListViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))) {
        Column {
            Text(
                text = "Watch List",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(15.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.movieList) { movie ->
                    WatchListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.WatchListDetailScreen.route+"/${movie.uuid}")
                    })
                }
            }
        }
        BottomMenuWatchList(navController = navController)
    }
}

 /*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen(
    navController: NavController,
    viewModel : WatchListViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Watch List",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            )
        },
        bottomBar = {
            BottomMenuWatchList(navController = navController)
        }
    ) {
        Column{
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.movieList) { movie ->
                    WatchListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.WatchListDetailScreen.route+"/${movie.uuid}")
                    })
                }
            }
        }
    }
}

 */