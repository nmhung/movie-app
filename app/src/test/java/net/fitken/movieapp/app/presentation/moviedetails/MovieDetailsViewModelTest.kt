//package net.fitken.movieapp.app.presentation.moviedetails
//
//import io.mockk.MockKAnnotations
//import io.mockk.coEvery
//import io.mockk.impl.annotations.MockK
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import net.fitken.domain.model.Genre
//import net.fitken.domain.model.Movie
//import net.fitken.domain.usecase.GetMovieDetailsUseCase
//import net.fitken.library.testutils.CoroutinesTestExtension
//import net.fitken.library.testutils.InstantTaskExecutorExtension
//import org.amshove.kluent.shouldBeEqualTo
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.RegisterExtension
//
//class MovieDetailsViewModelTest {
//
//    @ExperimentalCoroutinesApi
//    @JvmField
//    @RegisterExtension
//    val coroutinesTestExtension = CoroutinesTestExtension()
//
//    @JvmField
//    @RegisterExtension
//    var instantTaskExecutorExtension = InstantTaskExecutorExtension()
//
//    @MockK
//    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
//
//    private lateinit var viewModel: MovieDetailsViewModel
//
//    @BeforeEach
//    fun setUp() {
//        MockKAnnotations.init(this)
//
//        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
//    }
//
//    @Test
//    fun `verify state when GetMovieDetailsUseCase returns a movie`() {
//        // given
//        val genre = Genre(1, "Fiction")
//        val movie = Movie(
//            "poster.png",
//            "overview here",
//            "2021/12/12",
//            1289,
//            "Test movie",
//            "en",
//            "Test Movie",
//            "backdrop.png",
//            0f,
//            232,
//            false,
//            8.7f,
//            arrayListOf(genre),
//            130
//        )
//        coEvery {
//            getMovieDetailsUseCase.execute(1289)
//        } returns GetMovieDetailsUseCase.Result.Success(movie)
//
//        // when
//        viewModel.loadData()
//
//        // then
//        viewModel.stateLiveData.value shouldBeEqualTo MovieDetailsViewModel.ViewState(
//            isLoading = false,
//            isError = false,
//            movie = movie,
//            error = null
//        )
//    }
//}