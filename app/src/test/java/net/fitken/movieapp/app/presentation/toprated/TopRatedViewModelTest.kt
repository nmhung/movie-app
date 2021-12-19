package net.fitken.movieapp.app.presentation.toprated

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.fitken.domain.model.Genre
import net.fitken.domain.model.Movie
import net.fitken.domain.usecase.GetTopRatedUseCase
import net.fitken.library.testutils.CoroutinesTestExtension
import net.fitken.library.testutils.InstantTaskExecutorExtension
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class TopRatedViewModelTest {
    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    private lateinit var mockGetTopRatedUseCase: GetTopRatedUseCase

    private lateinit var viewModel: TopRatedViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = TopRatedViewModel(mockGetTopRatedUseCase)
    }

    @Test
    fun `execute getTopRatedUseCase`() {
        // when
        viewModel.loadData()

        // then
        coVerify { mockGetTopRatedUseCase.execute(1) }
    }

    @Test
    fun `verify state when GetTopRatedUseCase returns non-empty list`() {
        // given
        val genre = Genre(1, "Fiction")
        val movie = Movie(
            "poster.png",
            "overview here",
            "2021/12/12",
            3,
            "Test movie",
            "en",
            "Test Movie",
            "backdrop.png",
            0f,
            232,
            false,
            8.7f,
            arrayListOf(genre),
            130
        )
        val movies = listOf(movie)
        coEvery {
            mockGetTopRatedUseCase.execute(1)
        } returns GetTopRatedUseCase.Result.Success(movies)

        // when
        viewModel.loadData()

        // then
        viewModel.stateLiveData.value shouldBeEqualTo TopRatedViewModel.ViewState(
            isRefreshing = false,
            isError = false,
            movies = movies,
            error = null
        )
    }

    @Test
    fun `verify state when GetTopRatedUseCase returns exception`() {
        // given
        val error = Exception()
        coEvery {
            mockGetTopRatedUseCase.execute(1)
        } returns GetTopRatedUseCase.Result.Error(error)

        // when
        viewModel.loadData()

        // then
        viewModel.stateLiveData.value shouldBeEqualTo TopRatedViewModel.ViewState(
            isRefreshing = false,
            isError = true,
            movies = listOf(),
            error = error
        )
    }
}