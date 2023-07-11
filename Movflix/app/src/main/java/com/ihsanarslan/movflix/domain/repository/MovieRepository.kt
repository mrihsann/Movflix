package com.ihsanarslan.movflix.domain.repository

import com.ihsanarslan.movflix.data.remote.MovieAPI
import com.ihsanarslan.movflix.data.remote.dto.MovieDetailDto
import com.ihsanarslan.movflix.data.remote.dto.MoviesDto

interface MovieRepository {
    suspend fun getMovies(search:String):MoviesDto
    suspend fun getMovieDetail(imdbID:String):MovieDetailDto
}