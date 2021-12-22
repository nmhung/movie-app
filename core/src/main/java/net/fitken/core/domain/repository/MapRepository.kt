package net.fitken.core.domain.repository

import net.fitken.core.domain.model.Direction

interface MapRepository {
    suspend fun getDirection(origin: String, destination: String, mapApiKey: String): Direction
}