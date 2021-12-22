package net.fitken.core.domain.usecase

import net.fitken.core.domain.model.Movie
import net.fitken.core.domain.repository.MovieRepository

class GetTopRatedUseCase(private val movieRepository: MovieRepository) {

    sealed class Result {
        data class Success(val data: List<Movie>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(page: Int): Result {
        return try {
            Result.Success(movieRepository.getTopRated(page))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}