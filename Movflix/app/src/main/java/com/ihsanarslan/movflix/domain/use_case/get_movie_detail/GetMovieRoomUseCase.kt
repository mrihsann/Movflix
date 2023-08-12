package com.ihsanarslan.movflix.domain.use_case.get_movie_detail

import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieRoomUseCase @Inject constructor(private val repository : RoomRepository) {

    fun executeGetMovieRoom(uuid: Int) : Flow<MovieDetail> = flow {
        try {
            val movieDetail = repository.getMovieRoomID(uuid)
            emit(movieDetail)
        } catch (e: HttpException) {
            println(e.localizedMessage)
        }
    }

}