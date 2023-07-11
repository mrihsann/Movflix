package com.ihsanarslan.movflix.domain.repository

import com.ihsanarslan.movflix.domain.model.MovieDetail

interface RoomRepository {
    suspend fun getMoviesRoom(): List<MovieDetail>
    suspend fun insertRoom(movieDetail: MovieDetail)
    suspend fun deleteRoom(movieDetail: MovieDetail)
}