package net.fitken.domain.repository

import net.fitken.domain.model.Direction

interface MapRepository {
    suspend fun getDirection(origin: String, destination: String, mapApiKey: String): Direction
}