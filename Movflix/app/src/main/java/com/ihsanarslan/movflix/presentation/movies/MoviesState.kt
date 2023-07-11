package com.ihsanarslan.movflix.presentation.movies

import com.ihsanarslan.movflix.domain.model.Movie

data class MoviesState(
    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search : String = "batman"
)