package net.fitken.core.domain.usecase

import net.fitken.core.domain.model.Direction
import net.fitken.core.domain.repository.MapRepository

class GetDirectionUseCase(private val mapRepository: MapRepository) {
    sealed class Result {
        data class Success(val data: Direction) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(origin: String, destination: String, mapApiKey: String): Result {
        return try {
            Result.Success(mapRepository.getDirection(origin, destination, mapApiKey))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}