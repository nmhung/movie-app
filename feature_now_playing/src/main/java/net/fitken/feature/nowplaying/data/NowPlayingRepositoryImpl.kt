package net.fitken.feature.nowplaying.data.network

import net.fitken.feature.nowplaying.data.network.service.NowPlayingService
import net.fitken.feature.nowplaying.domain.model.Movie
import net.fitken.feature.nowplaying.domain.repository.NowPlayingRepository

internal class NowPlayingRepositoryImpl(private val nowPlayingService: NowPlayingService) :
    NowPlayingRepository {
    override suspend fun getNowPlaying(page: Int): List<Movie> {
        nowPlayingService.getNowPlaying(page)
        return emptyList()
    }
}