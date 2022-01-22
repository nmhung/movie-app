package net.fitken.data.repository

import net.fitken.data.network.model.toDomainModel
import net.fitken.data.network.service.MapService
import net.fitken.domain.model.Direction
import net.fitken.domain.repository.MapRepository

class MapRepositoryImpl(private val mapService: MapService) : MapRepository {
    override suspend fun getDirection(
        origin: String,
        destination: String,
        mapApiKey: String
    ): Direction =
        mapService.getDirection(origin, destination, mapApiKey).toDomainModel()

}