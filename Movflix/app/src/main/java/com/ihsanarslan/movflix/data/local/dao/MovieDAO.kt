package com.ihsanarslan.movflix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ihsanarslan.movflix.domain.model.MovieDetail

@Dao
interface MovieDAO {

    @Insert
    suspend fun insert(movieDetail: MovieDetail)

    @Query("DELETE FROM Movie WHERE Title = :title")
    suspend fun deleteByTitle(title: String)

    @Query("SELECT*FROM Movie")
    suspend fun getAllMovie(): List<MovieDetail>

    @Query("SELECT * FROM Movie WHERE Title = :title")
    suspend fun getMovieByTitle(title: String): MovieDetail

    @Query("SELECT * FROM Movie WHERE uuid = :uuid")
    suspend fun getMovieByID(uuid: Int): MovieDetail

}