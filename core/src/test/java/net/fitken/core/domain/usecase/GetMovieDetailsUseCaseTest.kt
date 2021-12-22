package net.fitken.core.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.fitken.core.domain.model.Genre
import net.fitken.core.domain.model.Movie
import net.fitken.core.domain.repository.MovieRepository
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class GetMovieDetailsUseCaseTest {

    @MockK
    private lateinit var mockMovieRepository: MovieRepository

    private lateinit var useCase: GetMovieDetailsUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        useCase = GetMovieDetailsUseCase(mockMovieRepository)
    }

    @Test
    fun `return movie details`() {
        // given
        val movieId = 12943323
        val genre = Genre(1, "Fiction")
        val movie = Movie(
            "poster.png",
            "overview here",
            "2021/12/12",
            movieId,
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
        coEvery {
            mockMovieRepository.getMovieDetails(movieId)
        } returns movie

        // when
        val result = runBlocking { useCase.execute(movieId) }

        // then
        result shouldBeEqualTo GetMovieDetailsUseCase.Result.Success(movie)
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val exception = UnknownHostException()
        coEvery {
            mockMovieRepository.getMovieDetails(12)
        } throws exception

        // when
        val result = runBlocking { useCase.execute(12) }

        // then
        result shouldBeEqualTo GetMovieDetailsUseCase.Result.Error(exception)
    }
}