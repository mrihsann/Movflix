package com.ihsanarslan.movflix.domain.use_case.get_movies

import com.ihsanarslan.movflix.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieRoomUseCase @Inject constructor(private val repository : RoomRepository) {

    fun searchMovie(title: String): Flow<Boolean> = flow {
        val movie = repository.searchRoom(title)
        emit(movie != null)
    }
}