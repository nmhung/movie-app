package net.fitken.movieapp.app.presentation.toprated

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.fitken.domain.model.Movie
import net.fitken.domain.usecase.GetTopRatedUseCase
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import javax.inject.Inject

@HiltViewModel
internal class TopRatedViewModel @Inject constructor(
    private val getTopRatedUseCase: GetTopRatedUseCase
) :
    BaseViewModel<TopRatedViewModel.ViewState, TopRatedViewModel.Action>(ViewState()) {

    private var page: Int = 1
    private val _movies = ArrayList<Movie>()

    override fun onLoadData() {
        getTopRated()
    }

    fun onRefresh() {
        sendAction(Action.StartRefreshing)
        page = 1
        getTopRated()
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
                        Action.TopRatedLoadingSuccess(_movies)
                    }

                    is GetTopRatedUseCase.Result.Error -> {
                        Action.TopRatedLoadingFailure(result.e)
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
        class TopRatedLoadingSuccess(val movies: List<Movie>) : Action()
        class TopRatedLoadingFailure(val error: Throwable?) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.TopRatedLoadingSuccess -> state.copy(
            isRefreshing = false,
            isError = false,
            movies = viewAction.movies,
            error = null
        )
        is Action.TopRatedLoadingFailure -> state.copy(
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