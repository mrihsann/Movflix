package com.ihsanarslan.movflix.domain.use_case.delete_movie

import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import javax.inject.Inject

class DeleteMovieRoomUseCase @Inject constructor(private val dao: MovieDAO) {

    suspend fun executeDeleteMovies(title:String) {
        dao.deleteByTitle(title)
    }


}