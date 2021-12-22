package net.fitken.core.data.repository

import net.fitken.core.data.network.model.toDomainModel
import net.fitken.core.data.network.service.MapService
import net.fitken.core.domain.model.Direction
import net.fitken.core.domain.repository.MapRepository

class MapRepositoryImpl(private val mapService: MapService) : MapRepository {
    override suspend fun getDirection(
        origin: String,
        destination: String,
        mapApiKey: String
    ): Direction =
        mapService.getDirection(origin, destination, mapApiKey).toDomainModel()

}