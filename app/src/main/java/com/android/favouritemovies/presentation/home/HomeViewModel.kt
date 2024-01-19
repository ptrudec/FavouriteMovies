package com.android.favouritemovies.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.usecase.GetFavoritesUseCase
import com.android.favouritemovies.domain.usecase.GetPopularMoviesUseCase
import com.android.favouritemovies.domain.usecase.GetTopRatedMoviesUseCase
import com.android.favouritemovies.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) :
    ViewModel() {
    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies get() = _favoriteMovies

    init {
        viewModelScope.launch {
            getFavoritesMovies()
        }

        getTopRatedMovies()
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            getTopRatedMoviesUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _moviesState.value = it
                }
        }
    }

    fun clearMovies() {
        moviesState.value = PagingData.empty()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _moviesState.value = it
                }
        }
    }

    private suspend fun getFavoritesMovies() {
        getFavoritesUseCase.execute(Unit)
            .collect {
                _favoriteMovies.value = it
            }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(movie)
            _favoriteMovies.value =
                if (_favoriteMovies.value.any { it.id == movie.id }) {
                    _favoriteMovies.value.filter { it.id != movie.id }
                } else {
                    _favoriteMovies.value + movie
                }
        }
    }
}