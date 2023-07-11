package com.ihsanarslan.movflix.domain.use_case.insert_movie

import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.domain.model.MovieDetail
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(private val dao: MovieDAO) {

    suspend fun executeInsertMovies(movieDetail: MovieDetail) {
        dao.insert(movieDetail = movieDetail)
    }
}