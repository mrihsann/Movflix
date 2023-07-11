package com.ihsanarslan.movflix.presentation.movies.view_model.movie_detail

import com.ihsanarslan.movflix.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)