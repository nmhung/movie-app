package net.fitken.data.repository

import net.fitken.data.network.model.toDomainModel
import net.fitken.data.network.service.MovieService
import net.fitken.domain.model.Movie
import net.fitken.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): List<Movie> =
        movieService.getNowPlaying(page).results.map {
            it.toDomainModel()
        }


    override suspend fun getTopRated(page: Int): List<Movie> =
        movieService.getTopRated(page).results.map { it.toDomainModel() }
}