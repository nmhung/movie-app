package net.fitken.feature.toprated.domain.usecase

import net.fitken.feature.toprated.domain.model.Movie
import net.fitken.feature.toprated.domain.repository.TopRatedRepository
import java.io.IOException

class GetTopRatedUseCase(private val topRatedRepository: TopRatedRepository) {

    sealed class Result {
        data class Success(val data: List<Movie>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(page: Int): Result {
        return try {
            Result.Success(topRatedRepository.getTopRated(page))
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}