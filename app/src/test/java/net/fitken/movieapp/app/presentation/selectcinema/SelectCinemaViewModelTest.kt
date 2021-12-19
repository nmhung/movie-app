package net.fitken.movieapp.app.presentation.selectcinema

import android.location.Geocoder
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.fitken.domain.model.Direction
import net.fitken.domain.model.Step
import net.fitken.domain.usecase.GetDirectionUseCase
import net.fitken.library.testutils.CoroutinesTestExtension
import net.fitken.library.testutils.InstantTaskExecutorExtension
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
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

    private val origin = "1.23,102.4"
    private val destination = "3.34,102.9"
    private val mapApiKey = "Az34AcvflgAdfazger-ffdESg"

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = SelectCinemaViewModel(mockGetDirectionUseCase, mockGeocoder)
    }

    @Disabled
    @Test
    fun `verify state when search an address returns a direction`() {
        // given
        val stepsDirection = ArrayList<Step>()
        stepsDirection.add(Step())
        val direction = Direction()
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