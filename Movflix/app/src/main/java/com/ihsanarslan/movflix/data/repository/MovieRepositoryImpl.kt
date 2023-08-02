package com.ihsanarslan.movflix.data.repository

import com.ihsanarslan.movflix.data.remote.MovieAPI
import com.ihsanarslan.movflix.data.remote.dto.MovieDetailDto
import com.ihsanarslan.movflix.data.remote.dto.MoviesDto
import com.ihsanarslan.movflix.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI): MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbID: String): MovieDetailDto {
        return api.getMovieDetail(imdbID = imdbID)
    }


}