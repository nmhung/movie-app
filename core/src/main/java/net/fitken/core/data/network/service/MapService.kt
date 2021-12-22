package net.fitken.core.data.network.service

import net.fitken.core.data.network.model.DirectionJson
import retrofit2.http.GET
import retrofit2.http.Query

interface MapService {
    @GET("https://maps.googleapis.com/maps/api/directions/json")
    suspend fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") mapApiKey: String
    ): DirectionJson
}