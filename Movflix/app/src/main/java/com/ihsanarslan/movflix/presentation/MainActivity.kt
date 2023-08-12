package com.ihsanarslan.movflix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.presentation.movies.view_model.movie_detail.MovieDetailViewModel
import com.ihsanarslan.movflix.presentation.movies.views.movie_detail.MovieDetailScreen
import com.ihsanarslan.movflix.presentation.movies.views.movie_detail.WatchListDetailScreen
import com.ihsanarslan.movflix.presentation.movies.views.movies.MovieScreen
import com.ihsanarslan.movflix.presentation.movies.views.watch_list_movies.WatchListScreen
import com.ihsanarslan.movflix.presentation.ui.theme.MovflixTheme
import com.ihsanarslan.movflix.util.Constants.IMDB_ID
import com.ihsanarslan.movflix.util.Constants.ROOM_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovflixTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.MovieScreen.route
                    ) {
                        composable(route = Screen.MovieScreen.route) {
                            MovieScreen(navController = navController)
                        }
                        composable(route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}") {
                            MovieDetailScreen(navController = navController)
                        }
                        composable(route = Screen.WatchListScreen.route) {
                            WatchListScreen(navController = navController)
                        }
                        composable(route = Screen.WatchListDetailScreen.route+"/{${ROOM_ID}}") {
                            WatchListDetailScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}