package net.fitken.domain.usecase

import net.fitken.domain.model.Movie
import net.fitken.domain.repository.MovieRepository
import java.io.IOException

class GetNowPlayingUseCase(private val movieRepository: MovieRepository) {

    sealed class Result {
        data class Success(val data: List<Movie>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(page: Int): Result {
        return try {
            Result.Success(movieRepository.getNowPlaying(page))
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}