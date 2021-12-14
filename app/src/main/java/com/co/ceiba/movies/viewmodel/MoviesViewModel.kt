package com.co.ceiba.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.services.MoviesService
import com.co.ceiba.infrastructure.dependencyInjection.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesService: MoviesService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val loading = MutableStateFlow(false)
    private val success = MutableStateFlow(MoviesUiState().success)
    private val error = MutableStateFlow(false)

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> get() = _uiState

    init {
        viewModelScope.launch {
            combine(
                loading,
                success,
                error
            ){ loading, success, error ->
                MoviesUiState (loading,success, error)

            }.catch { throwable ->
                throw throwable
            }.collect {
                _uiState.value = it
            }
        }
        getMovies()
    }

    fun getMovies (){
        viewModelScope.launch(ioDispatcher) {
            loading.value = true
            moviesService.invoke().collect { movies ->
                success.value = movies
                loading.value = false
            }

        }
    }

}


data class MoviesUiState (
    var loading: Boolean = false,
    var success: List<Movie> = emptyList(),
    var error: Boolean = false
)


