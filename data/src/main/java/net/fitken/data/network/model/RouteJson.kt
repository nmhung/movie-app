package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.fitken.domain.model.Route

@JsonClass(generateAdapter = true)
data class RouteJson(
    @Json(name = "legs")
    var legs: List<LegJson> = listOf()
)

fun RouteJson.toDomainModel(): Route = Route(legs.map { it.toDomainModel() })