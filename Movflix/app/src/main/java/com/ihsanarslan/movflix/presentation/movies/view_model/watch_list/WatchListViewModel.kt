package com.ihsanarslan.movflix.presentation.movies.view_model.watch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsanarslan.movflix.data.repository.RoomRepositoryImpl
import com.ihsanarslan.movflix.domain.model.MovieDetail
import com.ihsanarslan.movflix.domain.use_case.get_movies.GetMoviesRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val repositoryImpl: RoomRepositoryImpl
) : ViewModel() {

    val movieList = mutableListOf<MovieDetail>()

    init {
        getMovies()
    }

    fun getMovies() {

        GetMoviesRoomUseCase(repository = repositoryImpl).executeGetMovies().onEach {
            movieList.addAll(it)
        }.launchIn(viewModelScope)
    }
}