package net.fitken.movieapp.app.presentation.movielist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.fitken.domain.model.Movie
import net.fitken.domain.usecase.GetNowPlayingUseCase
import net.fitken.domain.usecase.GetTopRatedUseCase
import net.fitken.movieapp.app.enums.MovieListTypeEnum
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase
) :
    BaseViewModel<MovieListViewModel.ViewState, MovieListViewModel.Action>(ViewState()) {

    private var page: Int = 1
    private val _movies = ArrayList<Movie>()
    private var _movieType = MovieListTypeEnum.NOW_PLAYING

    fun init(movieListTypeEnum: MovieListTypeEnum) {
        _movieType = movieListTypeEnum
    }

    override fun onLoadData() {
        when (_movieType) {
            MovieListTypeEnum.NOW_PLAYING -> getNowPlaying()
            MovieListTypeEnum.TOP_RATED -> getTopRated()
        }
    }

    fun onRefresh() {
        sendAction(Action.StartRefreshing)
        page = 1
        loadData()
    }

    fun loadMore() {
        page++
        loadData()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            getTopRatedUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetTopRatedUseCase.Result.Success -> {
                        if (state.isRefreshing) {
                            _movies.clear()
                        }
                        _movies.addAll(result.data)
                        Action.LoadingSuccess(_movies)
                    }

                    is GetTopRatedUseCase.Result.Error -> {
                        Action.LoadingFailure(result.e)
                    }
                }
                sendAction(action)
            }
        }
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            getNowPlayingUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetNowPlayingUseCase.Result.Success -> {
                        if (state.isRefreshing) {
                            _movies.clear()
                        }
                        _movies.addAll(result.data)
                        Action.LoadingSuccess(_movies)
                    }

                    is GetNowPlayingUseCase.Result.Error -> {
                        Action.LoadingFailure(result.e)
                    }
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
        val isRefreshing: Boolean = true,
        val isError: Boolean = false,
        val movies: List<Movie> = listOf(),
        val error: Throwable? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object StartRefreshing : Action()
        class LoadingSuccess(val movies: List<Movie>) : Action()
        class LoadingFailure(val error: Throwable?) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.LoadingSuccess -> state.copy(
            isRefreshing = false,
            isError = false,
            movies = viewAction.movies,
            error = null
        )
        is Action.LoadingFailure -> state.copy(
            isRefreshing = false,
            isError = true,
            movies = listOf(),
            error = viewAction.error
        )
        Action.StartRefreshing -> state.copy(
            isRefreshing = true,
            isError = false,
            movies = listOf(),
            error = null
        )
    }
}