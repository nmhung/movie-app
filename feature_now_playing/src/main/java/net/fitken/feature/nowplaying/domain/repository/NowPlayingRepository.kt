package net.fitken.feature.nowplaying.domain.repository

import net.fitken.feature.nowplaying.domain.model.Movie

interface NowPlayingRepository {
    suspend fun getNowPlaying(page: Int): List<Movie>
}