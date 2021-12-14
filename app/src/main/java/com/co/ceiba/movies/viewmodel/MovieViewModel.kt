package com.co.ceiba.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.services.MovieService
import com.co.ceiba.infrastructure.dependencyInjection.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieService: MovieService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val loading = MutableStateFlow(false)
    private val success = MutableStateFlow(MovieUiState().success)
    private val error = MutableStateFlow(false)

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> get() = _uiState

    init {
        viewModelScope.launch {
            combine(
                loading,
                success,
                error
            ){ loading, success, error ->
                MovieUiState (loading,success, error)

            }.catch { throwable ->
                throw throwable
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun getMovie (id:Int){
        viewModelScope.launch(ioDispatcher) {
            loading.value = true
            movieService.invoke(id).collect { movies ->
                success.value = movies
                loading.value = false
            }

        }
    }

}


data class MovieUiState (
    var loading: Boolean = false,
    var success: Movie? = null,
    var error: Boolean = false
)
