package com.ihsanarslan.movflix.presentation.movies

sealed  class MoviesEvent {
    data class Search(val searchString :String) : MoviesEvent()
}