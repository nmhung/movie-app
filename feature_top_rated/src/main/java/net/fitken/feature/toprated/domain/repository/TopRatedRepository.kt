package net.fitken.feature.toprated.domain.repository

import net.fitken.feature.toprated.domain.model.Movie

interface TopRatedRepository {
    suspend fun getTopRated(page: Int): List<Movie>
}