package com.ihsanarslan.movflix.data.remote.dto

import com.ihsanarslan.movflix.domain.model.Movie

data class MoviesDto(
    val Resource : String,
    val Search : List<Search>,
    val TotalResults : String,
    val Response: String)

fun MoviesDto.toMovieList():List<Movie>{
    return Search.map { search -> Movie(search.Poster,search.Title,search.Year,search.imdbID,search.Type) }
}