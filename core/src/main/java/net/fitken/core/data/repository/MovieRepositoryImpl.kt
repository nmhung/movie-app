package net.fitken.core.data.repository

import net.fitken.core.data.network.model.toDomainModel
import net.fitken.core.data.network.service.MovieService
import net.fitken.core.domain.model.Movie
import net.fitken.core.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): List<Movie> =
        movieService.getNowPlaying(page).results.map {
            it.toDomainModel()
        }


    override suspend fun getTopRated(page: Int): List<Movie> =
        movieService.getTopRated(page).results.map { it.toDomainModel() }

    override suspend fun getMovieDetails(id: Int): Movie =
        movieService.getMovieDetails(id).toDomainModel()
}