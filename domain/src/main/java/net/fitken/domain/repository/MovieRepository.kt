package net.fitken.domain.repository

import net.fitken.domain.model.Movie

interface MovieRepository {
    suspend fun getNowPlaying(page: Int): List<Movie>
    suspend fun getTopRated(page: Int): List<Movie>
    suspend fun getMovieDetails(id: Int): Movie
}