package net.fitken.movieapp.app.presentation.nowplaying

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.fitken.core.domain.model.Genre
import net.fitken.core.domain.model.Movie
import net.fitken.core.domain.usecase.GetNowPlayingUseCase
import net.fitken.library.testutils.CoroutinesTestExtension
import net.fitken.library.testutils.InstantTaskExecutorExtension
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class NowPlayingViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    private lateinit var mockGetNowPlayingUseCase: GetNowPlayingUseCase

    private lateinit var viewModel: NowPlayingViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = NowPlayingViewModel(mockGetNowPlayingUseCase)
    }

    @Test
    fun `execute getNowPlayingUseCase`() {
        // when
        viewModel.loadData()

        // then
        coVerify { mockGetNowPlayingUseCase.execute(1) }
    }

    @Test
    fun `verify state when GetNowPlayingUseCase returns non-empty list`() {
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
            mockGetNowPlayingUseCase.execute(1)
        } returns GetNowPlayingUseCase.Result.Success(movies)

        // when
        viewModel.loadData()

        // then
        viewModel.stateLiveData.value shouldBeEqualTo NowPlayingViewModel.ViewState(
            isRefreshing = false,
            isError = false,
            movies = movies,
            error = null
        )
    }

    @Test
    fun `verify state when GetNowPlayingUseCase returns exception`() {
        // given
        val error = Exception()
        coEvery {
            mockGetNowPlayingUseCase.execute(1)
        } returns GetNowPlayingUseCase.Result.Error(error)

        // when
        viewModel.loadData()

        // then
        viewModel.stateLiveData.value shouldBeEqualTo NowPlayingViewModel.ViewState(
            isRefreshing = false,
            isError = true,
            movies = listOf(),
            error = error
        )
    }
}