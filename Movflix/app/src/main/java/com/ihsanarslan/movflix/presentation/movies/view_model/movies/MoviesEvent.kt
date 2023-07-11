package com.ihsanarslan.movflix.presentation.movies.view_model.movies

sealed  class MoviesEvent {
    data class Search(val searchString :String) : MoviesEvent()
}