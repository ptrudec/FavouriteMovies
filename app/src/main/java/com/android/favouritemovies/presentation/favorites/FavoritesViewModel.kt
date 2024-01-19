package com.android.favouritemovies.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.usecase.GetFavoritesUseCase
import com.android.favouritemovies.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> get() = _favoriteMovies

    init {
        viewModelScope.launch {
            fetchFavoritesMovies()
        }
    }

    private suspend fun fetchFavoritesMovies() {
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