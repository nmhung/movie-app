package net.fitken.feature.nowplaying.domain.usecase

import net.fitken.feature.nowplaying.domain.model.Movie
import net.fitken.feature.nowplaying.domain.repository.NowPlayingRepository
import java.io.IOException

class GetNowPlayingUseCase(private val nowPlayingRepository: NowPlayingRepository) {

    sealed class Result {
        data class Success(val data: List<Movie>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(page: Int): Result {
        return try {
            Result.Success(nowPlayingRepository.getNowPlaying(page))
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}