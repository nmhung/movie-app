package net.fitken.domain.usecase

import net.fitken.domain.model.Movie
import net.fitken.domain.repository.MovieRepository

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