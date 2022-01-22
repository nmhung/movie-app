package net.fitken.domain.usecase

import net.fitken.domain.model.Movie
import net.fitken.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {

    sealed class Result {
        data class Success(val data: Movie) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(id: Int): Result {
        return try {
            Result.Success(movieRepository.getMovieDetails(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}