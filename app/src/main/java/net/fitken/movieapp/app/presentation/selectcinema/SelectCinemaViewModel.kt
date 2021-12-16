package net.fitken.movieapp.app.presentation.selectcinema

import android.location.Address
import android.location.Geocoder
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import java.io.IOException

internal class SelectCinemaViewModel :
    BaseViewModel<SelectCinemaViewModel.ViewState, SelectCinemaViewModel.Action>(ViewState()) {

    private lateinit var _geocoder: Geocoder

    fun init(geocoder: Geocoder) {
        _geocoder = geocoder
    }

    fun loadAddress(query: String) {
        var list: List<Address> = ArrayList()
        try {
            sendAction(Action.StartRefreshing)
            list = _geocoder.getFromLocationName(query, 1)
        } catch (e: IOException) {
            e.printStackTrace()
            sendAction(Action.LoadingFailure)
        }
        if (list.isNotEmpty()) {
            val address = list[0]
            sendAction(Action.LoadingSuccess(address))
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val address: Address? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object StartRefreshing : Action()
        class LoadingSuccess(val address: Address) : Action()
        object LoadingFailure : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.LoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            address = viewAction.address
        )
        is Action.LoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            address = null
        )
        Action.StartRefreshing -> state.copy(
            isLoading = true,
            isError = false,
            address = null
        )
    }
}