package com.ihsanarslan.movflix.domain.use_case.get_movies

import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesRoomUseCase @Inject constructor(private val repository : RoomRepository) {

    fun executeGetMovies() : Flow<List<MovieDetail>> = flow {
        val movieList = repository.getMoviesRoom()
        emit(movieList)
    }
}