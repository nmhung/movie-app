package net.fitken.feature.toprated.data

import net.fitken.feature.toprated.data.network.model.toDomainModel
import net.fitken.feature.toprated.data.network.service.TopRatedService
import net.fitken.feature.toprated.domain.model.Movie
import net.fitken.feature.toprated.domain.repository.TopRatedRepository

internal class TopRatedRepositoryImpl(private val topRatedService: TopRatedService) :
    TopRatedRepository {
    override suspend fun getTopRated(page: Int): List<Movie> =
        topRatedService.getTopRated(page).results.map { it.toDomainModel() }

}