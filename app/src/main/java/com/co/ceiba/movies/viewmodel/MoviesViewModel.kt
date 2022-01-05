package com.co.ceiba.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.co.ceiba.domain.exceptions.NoDataMovie
import com.co.ceiba.domain.exceptions.UnknownException
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.services.MoviesService
import com.co.ceiba.infrastructure.dependencyInjection.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesService: MoviesService,
    @DefaultDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val loading = MutableStateFlow(MoviesUiState().loading)
    private val success = MutableStateFlow(MoviesUiState().success)
    private val error = MutableStateFlow(MoviesUiState().error)
    private val message = MutableStateFlow(MoviesUiState().message)

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> get() = _uiState

    init {
        viewModelScope.launch {
            combine(
                loading,
                success,
                error,
                message
            ) { loading, success, error, message ->
                MoviesUiState(loading, success, error, message)
            }.catch { throwable ->
                throw throwable
            }.collect {
                _uiState.value = it
            }
        }
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(ioDispatcher) {
            loading.value = true
            try {
                moviesService.invoke().collect { movies ->
                    success.value = movies
                    loading.value = false
                }
            } catch (e: Exception) {
                loading.value = false
                if(e is NoDataMovie) message.value = "We haven't movies to show"
                else if (e is UnknownException) message.value = "We are having problems, please try later"
                error.value = true
            }

        }
    }

}


data class MoviesUiState(
    var loading: Boolean = false,
    var success: List<Movie> = emptyList(),
    var error: Boolean = false,
    var message: String = "All was good"
)


