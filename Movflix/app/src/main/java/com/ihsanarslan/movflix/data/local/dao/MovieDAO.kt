package com.ihsanarslan.movflix.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ihsanarslan.movflix.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Insert
    suspend fun insert(movieDetail: MovieDetail)

    @Delete
    suspend fun delete(movieDetail: MovieDetail)

    @Query("SELECT*FROM Movies")
    suspend fun getAllMovie(): Flow<List<MovieDetail>>
}