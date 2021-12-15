package net.fitken.movieapp.app.presentation.moviedetails

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.fitken.domain.model.Movie
import net.fitken.domain.usecase.GetMovieDetailsUseCase
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) :
    BaseViewModel<MovieDetailsViewModel.ViewState, MovieDetailsViewModel.Action>(
        ViewState()
    ) {

    var movieId: Int = 0

    fun init(id: Int) {
        movieId = id
    }

    override fun onLoadData() {
        getMovieDetail()
    }

    fun onRefresh() {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            sendAction(Action.StartRefreshing)
            getMovieDetailsUseCase.execute(movieId).also { result ->
                val action = when (result) {
                    is GetMovieDetailsUseCase.Result.Success -> {
                        Action.MovieDetailsLoadingSuccess(result.data)
                    }

                    is GetMovieDetailsUseCase.Result.Error -> {
                        Action.MovieDetailsLoadingFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val movie: Movie? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object StartRefreshing : Action()
        class MovieDetailsLoadingSuccess(val movie: Movie) : Action()
        object MovieDetailsLoadingFailure : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.MovieDetailsLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movie = viewAction.movie
        )
        is Action.MovieDetailsLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            movie = null
        )
        Action.StartRefreshing -> state.copy(
            isLoading = true,
            isError = false,
            movie = null
        )
    }
}