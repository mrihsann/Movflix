package com.ihsanarslan.movflix.presentation.movies.view_model.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.data.repository.RoomRepositoryImpl
import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.use_case.delete_movie.DeleteMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movie_detail.GetMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movies.GetMoviesRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.get_movies.SearchMovieRoomUseCase
import com.ihsanarslan.movflix.domain.use_case.insert_movie.InsertMovieRoomUseCase
import com.ihsanarslan.movflix.util.Constants.IMDB_ID
import com.ihsanarslan.movflix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val dao: MovieDAO,
    private val repositoryImpl: RoomRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState> = _state

    private val _isMovieFound = mutableStateOf(false)
    val isMovieFound: State<Boolean> = _isMovieFound

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId = imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")

                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun favMovie(liked:Boolean,movieDetail: MovieDetail,title: String) {
        viewModelScope.launch {
            if (liked) {
                InsertMovieRoomUseCase(dao).executeInsertMovies(movieDetail)
                println("eklendi")
            }
            else{
                DeleteMovieRoomUseCase(dao).executeDeleteMovies(title)
                println("silindi")
            }
        }
    }

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

    fun getMovieRoom(title: String) {
        viewModelScope.launch {
            val movieFlow = GetMovieRoomUseCase(repository = repositoryImpl).executeGetMovieRoom(title)
            movieFlow.collect { movieList ->
                println(movieList)
            }
        }
    }

    fun searchMovie(title: String){
        viewModelScope.launch {
            SearchMovieRoomUseCase(repositoryImpl).searchMovie(title).collect { movie ->
                //filmin var olma durumuna göre işlem yapıyoruz.
                if (movie) {
                    println("Film mevcut")
                    _isMovieFound.value=true

                } else {
                    println("Film mevcut değil")
                    _isMovieFound.value=false
                }
            }
        }
    }

}