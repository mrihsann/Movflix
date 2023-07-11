package com.ihsanarslan.movflix.domain.use_case.delete_movie

import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.domain.model.MovieDetail
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(private val dao: MovieDAO) {

    suspend fun executeDeleteMovies(movieDetail: MovieDetail) {
        dao.delete(movieDetail = movieDetail)
    }

}