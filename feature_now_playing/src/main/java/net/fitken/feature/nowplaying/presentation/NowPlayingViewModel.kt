package net.fitken.feature.nowplaying.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.fitken.feature.nowplaying.domain.model.Movie
import net.fitken.feature.nowplaying.domain.usecase.GetNowPlayingUseCase
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState

internal class NowPlayingViewModel :
    BaseViewModel<NowPlayingViewModel.ViewState, NowPlayingViewModel.Action>(ViewState()) {

    private lateinit var _getNowPlayingUseCase: GetNowPlayingUseCase
    private var page: Int = 1

    fun setViewModelAttributes(getNowPlayingUseCase: GetNowPlayingUseCase) {
        _getNowPlayingUseCase = getNowPlayingUseCase
    }

    override fun onLoadData() {
        getNowPlaying()
    }

    fun onRefresh() {
        getNowPlaying()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            sendAction(Action.StartRefreshing)
            _getNowPlayingUseCase.execute(page).also { result ->
                val action = when (result) {
                    is GetNowPlayingUseCase.Result.Success -> {
                        Action.NowPlayingLoadingSuccess(result.data)
                    }

                    is GetNowPlayingUseCase.Result.Error -> {
                        Action.NowPlayingLoadingFailure
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
        class NowPlayingLoadingSuccess(val movies: List<Movie>) : Action()
        object NowPlayingLoadingFailure : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.NowPlayingLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movies = viewAction.movies
        )
        is Action.NowPlayingLoadingFailure -> state.copy(
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