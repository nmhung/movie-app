package net.fitken.movieapp.app.presentation.selectcinema

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.fitken.core.domain.model.Step
import net.fitken.core.domain.usecase.GetDirectionUseCase
import net.fitken.movieapp.BuildConfig
import net.fitken.movieapp.base.viewmodel.BaseAction
import net.fitken.movieapp.base.viewmodel.BaseViewModel
import net.fitken.movieapp.base.viewmodel.BaseViewState
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
internal class SelectCinemaViewModel @Inject constructor(
    private val getDirectionUseCase: GetDirectionUseCase,
    private val geocoder: Geocoder
) :
    BaseViewModel<SelectCinemaViewModel.ViewState, SelectCinemaViewModel.Action>(ViewState()) {

    private var originLat: Double = 0.0
    private var originLng: Double = 0.0
    private var destinationLat: Double = 0.0
    private var destinationLng: Double = 0.0

    fun loadAddress(query: String, currentLocation: LatLng?) {
        viewModelScope.launch {
            var list: List<Address> = ArrayList()
            try {
                sendAction(Action.StartRefreshing)
                list = geocoder.getFromLocationName(query, 1)
            } catch (e: IOException) {
                e.printStackTrace()
                sendAction(Action.LoadingFailure(e))
            }
            if (list.isNotEmpty()) {
                val address = list[0]
                sendAction(Action.LoadingSuccess(address))

                currentLocation?.let { curr ->
                    originLat = curr.latitude
                    originLng = curr.longitude
                    destinationLat = address.latitude
                    destinationLng = address.longitude
                    loadData()
                }
            }
        }
    }

    override fun onLoadData() {
        getDirection()
    }

    private fun getDirection() {
        viewModelScope.launch {
            getDirectionUseCase.execute(
                "$originLat,$originLng",
                "$destinationLat,$destinationLng",
                BuildConfig.MAPS_API_KEY
            ).also { result ->
                val action = when (result) {
                    is GetDirectionUseCase.Result.Success -> {
                        val stepsDirection = ArrayList<Step>()
                        result.data.routes.forEach { route ->
                            route.legs.forEach { leg ->
                                stepsDirection.addAll(leg.steps)
                            }
                        }

                        Action.StepDirectionLoadingSuccess(stepsDirection)
                    }
                    is GetDirectionUseCase.Result.Error -> {
                        Action.LoadingFailure(result.e)
                    }
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val address: Address? = null,
        val stepDirection: ArrayList<Step>? = null,
        val error: Throwable? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object StartRefreshing : Action()
        class LoadingSuccess(val address: Address) : Action()
        class StepDirectionLoadingSuccess(val stepDirection: ArrayList<Step>) : Action()
        class LoadingFailure(val error: Throwable?) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.LoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            address = viewAction.address,
            stepDirection = null,
            error = null
        )
        is Action.StepDirectionLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            address = null,
            stepDirection = viewAction.stepDirection,
            error = null
        )
        is Action.LoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            address = null,
            stepDirection = null,
            error = viewAction.error
        )
        Action.StartRefreshing -> state.copy(
            isLoading = true,
            isError = false,
            address = null,
            stepDirection = null,
            error = null
        )
    }
}