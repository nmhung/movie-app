package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RouteJson(
    @Json(name = "legs")
    var legs: List<LegJson> = listOf()
)