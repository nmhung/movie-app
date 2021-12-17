package net.fitken.movieapp.app.presentation.nowplaying

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.fitken.domain.model.Movie
import net.fitken.domain.usecase.GetNowPlayingUseCase
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import javax.inject.Inject

@HiltViewModel
internal class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) :
    BaseViewModel<NowPlayingViewModel.ViewState, NowPlayingViewModel.Action>(ViewState()) {

    private var page: Int = 1

    override fun onLoadData() {
        getNowPlaying()
    }

    fun onRefresh() {
        getNowPlaying()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            sendAction(Action.StartRefreshing)
            getNowPlayingUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetNowPlayingUseCase.Result.Success -> {
                        Action.NowPlayingLoadingSuccess(result.data)
                    }

                    is GetNowPlayingUseCase.Result.Error -> {
                        Action.NowPlayingLoadingFailure(result.e)
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
        class NowPlayingLoadingSuccess(val movies: List<Movie>) : Action()
        class NowPlayingLoadingFailure(val error: Throwable?) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.NowPlayingLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movies = viewAction.movies,
            error = null
        )
        is Action.NowPlayingLoadingFailure -> state.copy(
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