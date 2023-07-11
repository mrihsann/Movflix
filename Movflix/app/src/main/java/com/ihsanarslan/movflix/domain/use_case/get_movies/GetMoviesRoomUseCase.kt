package com.ihsanarslan.movflix.domain.use_case.get_movies

import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.repository.RoomRepository
import com.ihsanarslan.movflix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesRoomUseCase @Inject constructor(private val repository : RoomRepository) {

    fun executeGetMovies() : Flow<Resource<List<MovieDetail>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMoviesRoom()
            emit(Resource.Success(movieList))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}