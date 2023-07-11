package com.ihsanarslan.movflix.data.remote

import com.ihsanarslan.movflix.data.remote.dto.MovieDetailDto
import com.ihsanarslan.movflix.data.remote.dto.MoviesDto
import com.ihsanarslan.movflix.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MoviesDto


    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbID: String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieDetailDto

}