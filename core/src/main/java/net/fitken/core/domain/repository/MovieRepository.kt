package net.fitken.core.domain.repository

import net.fitken.core.domain.model.Movie

interface MovieRepository {
    suspend fun getNowPlaying(page: Int): List<Movie>
    suspend fun getTopRated(page: Int): List<Movie>
    suspend fun getMovieDetails(id: Int): Movie
}