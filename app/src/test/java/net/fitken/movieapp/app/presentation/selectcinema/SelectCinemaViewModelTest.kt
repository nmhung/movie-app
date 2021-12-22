package net.fitken.movieapp.app.presentation.selectcinema

import android.location.Geocoder
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.fitken.core.domain.model.*
import net.fitken.core.domain.usecase.GetDirectionUseCase
import net.fitken.library.testutils.CoroutinesTestExtension
import net.fitken.library.testutils.InstantTaskExecutorExtension
import net.fitken.movieapp.BuildConfig
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class SelectCinemaViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    private lateinit var mockGetDirectionUseCase: GetDirectionUseCase

    @MockK
    private lateinit var mockGeocoder: Geocoder

    private lateinit var viewModel: SelectCinemaViewModel

    private val origin = "0.0,0.0"
    private val destination = "0.0,0.0"
    private val mapApiKey = BuildConfig.MAPS_API_KEY

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = SelectCinemaViewModel(mockGetDirectionUseCase, mockGeocoder)
    }

    @Test
    fun `verify state when search an address returns a direction`() {
        // given
        val stepsDirection = ArrayList<Step>()
        stepsDirection.add(Step())
        val leg = Leg("", Location(), "", Location(), stepsDirection)
        val route = Route(listOf(leg))
        val direction = Direction(listOf(route))
        coEvery {
            mockGetDirectionUseCase.execute(origin, destination, mapApiKey)
        } returns GetDirectionUseCase.Result.Success(direction)

        // when
        viewModel.loadData()

        // then
        viewModel.stateLiveData.value shouldBeEqualTo SelectCinemaViewModel.ViewState(
                isLoading = false,
                isError = false,
                address = null,
                stepDirection = stepsDirection,
                error = null
        )
    }
}