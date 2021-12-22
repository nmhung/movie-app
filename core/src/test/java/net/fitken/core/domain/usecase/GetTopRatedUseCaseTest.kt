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

class GetTopRatedUseCaseTest {

    @MockK
    private lateinit var mockMovieRepository: MovieRepository

    private lateinit var useCase: GetTopRatedUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        useCase = GetTopRatedUseCase(mockMovieRepository)
    }

    @Test
    fun `return top rated movies`() {
        // given
        val genre = Genre(1, "Fiction")
        val movie = Movie(
            "poster.png",
            "overview here",
            "2021/12/12",
            1289,
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
            mockMovieRepository.getTopRated(1)
        } returns listOf(movie)

        // when
        val result = runBlocking { useCase.execute(1) }

        // then
        result shouldBeEqualTo GetTopRatedUseCase.Result.Success(listOf(movie))
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val exception = UnknownHostException()
        coEvery {
            mockMovieRepository.getTopRated(1)
        } throws exception

        // when
        val result = runBlocking { useCase.execute(1) }

        // then
        result shouldBeEqualTo GetTopRatedUseCase.Result.Error(exception)
    }
}