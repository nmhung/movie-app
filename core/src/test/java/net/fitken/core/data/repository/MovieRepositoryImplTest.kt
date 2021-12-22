package net.fitken.core.data.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.fitken.core.data.network.model.*
import net.fitken.core.data.network.service.MovieService
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieRepositoryImplTest {

    @MockK
    private lateinit var mockService: MovieService

    private lateinit var repositoryImpl: MovieRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        repositoryImpl = MovieRepositoryImpl(mockService)
    }

    @Test
    fun `get Now Playing movies and maps to Model`() {
        // given
        val genreJson = GenreJson(1, "Fiction")
        val movieJson = MovieJson(
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
            arrayListOf(genreJson),
            130
        )
        coEvery {
            mockService.getNowPlaying(1)
        } returns NowPlayingJson(1, 1, 1, listOf(movieJson))

        // when
        val result = runBlocking { repositoryImpl.getNowPlaying(1) }

        // then
        result shouldBeEqualTo listOf(movieJson).map { it.toDomainModel() }
    }

    @Test
    fun `get Now Playing movies returns empty`() {
        // given
        coEvery {
            mockService.getNowPlaying(1)
        } returns NowPlayingJson(1, 0, 0, listOf())

        // when
        val result = runBlocking { repositoryImpl.getNowPlaying(1) }

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `get Top Rated movies and maps to Model`() {
        // given
        val genreJson = GenreJson(1, "Fiction")
        val movieJson = MovieJson(
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
            arrayListOf(genreJson),
            130
        )
        coEvery {
            mockService.getTopRated(1)
        } returns TopRatedJson(1, 1, 1, listOf(movieJson))

        // when
        val result = runBlocking { repositoryImpl.getTopRated(1) }

        // then
        result shouldBeEqualTo listOf(movieJson).map { it.toDomainModel() }
    }

    @Test
    fun `get Top Rated movies returns empty`() {
        // given
        coEvery {
            mockService.getTopRated(1)
        } returns TopRatedJson(1, 0, 0, listOf())

        // when
        val result = runBlocking { repositoryImpl.getTopRated(1) }

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `get movie details and maps to Model`() {
        // given
        val movieId = 12943323
        val genreJson = GenreJson(1, "Fiction")
        val movieJson = MovieJson(
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
            arrayListOf(genreJson),
            130
        )
        coEvery {
            mockService.getMovieDetails(movieId)
        } returns movieJson

        // when
        val result = runBlocking { repositoryImpl.getMovieDetails(movieId) }

        // then
        result shouldBeEqualTo movieJson.toDomainModel()
    }
}