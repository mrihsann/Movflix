package com.ihsanarslan.movflix.data.repository

import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao: MovieDAO): RoomRepository {
    override suspend fun getMoviesRoom(): List<MovieDetail> {
        return dao.getAllMovie()
    }

    override suspend fun insertRoom(movieDetail: MovieDetail) {
        return dao.insert(movieDetail=movieDetail)
    }

    override suspend fun deleteRoom(movieDetail: MovieDetail) {
        return dao.delete(movieDetail = movieDetail)
    }
}