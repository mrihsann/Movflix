package com.ihsanarslan.movflix.presentation

sealed class Screen (val route:String) {
    object MovieScreen : Screen("movie_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
    object WatchListScreen : Screen("watch_list_screen")
    object WatchListDetailScreen : Screen("watch_list_detail_screen")


}