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

    override fun onLoadData() {
        getTopRated()
    }

    fun onRefresh() {
        getTopRated()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            sendAction(Action.StartRefreshing)
            getTopRatedUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetTopRatedUseCase.Result.Success -> {
                        Action.TopRatedLoadingSuccess(result.data)
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
        val isLoading: Boolean = true,
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
            isLoading = false,
            isError = false,
            movies = viewAction.movies,
            error = null
        )
        is Action.TopRatedLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            movies = listOf(),
            error = viewAction.error
        )
        Action.StartRefreshing -> state.copy(
            isLoading = true,
            isError = false,
            movies = listOf(),
            error = null
        )
    }
}