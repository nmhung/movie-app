package net.fitken.feature.toprated.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.fitken.feature.toprated.domain.model.Movie
import net.fitken.feature.toprated.domain.usecase.GetTopRatedUseCase
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState

internal class TopRatedViewModel :
    BaseViewModel<TopRatedViewModel.ViewState, TopRatedViewModel.Action>(ViewState()) {

    private lateinit var _getTopRatedUseCase: GetTopRatedUseCase
    private var page: Int = 1

    fun setViewModelAttributes(getTopRatedUseCase: GetTopRatedUseCase) {
        _getTopRatedUseCase = getTopRatedUseCase
    }

    override fun onLoadData() {
        getTopRated()
    }

    fun onRefresh() {
        getTopRated()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            sendAction(Action.StartRefreshing)
            _getTopRatedUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetTopRatedUseCase.Result.Success -> {
                        Action.TopRatedLoadingSuccess(result.data)
                    }

                    is GetTopRatedUseCase.Result.Error -> {
                        Action.TopRatedLoadingFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val movies: List<Movie> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object StartRefreshing : Action()
        class TopRatedLoadingSuccess(val movies: List<Movie>) : Action()
        object TopRatedLoadingFailure : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.TopRatedLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movies = viewAction.movies
        )
        is Action.TopRatedLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            movies = listOf()
        )
        Action.StartRefreshing -> state.copy(
            isLoading = true,
            isError = false,
            movies = listOf()
        )
    }
}