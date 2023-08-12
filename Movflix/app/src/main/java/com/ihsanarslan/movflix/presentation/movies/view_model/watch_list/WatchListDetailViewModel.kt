package com.ihsanarslan.movflix.presentation.movies.view_model.watch_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.data.repository.RoomRepositoryImpl
import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.use_case.delete_movie.DeleteMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movie_detail.GetMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movies.GetMoviesRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movies.SearchMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.insert_movie.InsertMovieRoomUseCase
import com.ihsanarslan.movflix.presentation.movies.view_model.movie_detail.MovieDetailState
import com.ihsanarslan.movflix.util.Constants.ROOM_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dao: MovieDAO,
    private val repositoryImpl: RoomRepositoryImpl
) : ViewModel() {

    var movieDetailRoom: MovieDetail? by mutableStateOf(null)


    private val _isMovieFound = mutableStateOf(false)
    val isMovieFound: State<Boolean> = _isMovieFound

    init {
        savedStateHandle.get<String>(ROOM_ID)?.let {
            getMovieById(it.toInt())
        }
    }

    fun favMovie(liked:Boolean,movieDetail: MovieDetail,title: String) {
        viewModelScope.launch {
            if (liked) {
                InsertMovieRoomUseCase(dao).executeInsertMovies(movieDetail)
            }
            else{
                DeleteMovieRoomUseCase(dao).executeDeleteMovies(title)
            }
        }
    }

    fun getMovieById(uuid:Int) {
        viewModelScope.launch {
            val movieFlow = GetMovieRoomUseCase(repository = repositoryImpl).executeGetMovieRoom(uuid)
            movieFlow.collect { movie ->
                movieDetailRoom=movie
            }
        }
    }

    /*
    fun getMovie() {
        viewModelScope.launch {
            val movieFlow = GetMoviesRoomUseCase(repository = repositoryImpl).executeGetMovies()
            movieFlow.collect { movieList ->
                for (movie in movieList) {
                    println(movie) // Varsayılan olarak, elemanları konsola yazdırır
                }
            }
        }
    }

     */

    fun searchMovie(title: String){
        viewModelScope.launch {
            SearchMovieRoomUseCase(repositoryImpl).searchMovie(title).collect { movie ->
                //filmin var olma durumuna göre işlem yapıyoruz.
                _isMovieFound.value = movie
            }
        }
    }

}