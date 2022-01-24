package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.fitken.domain.model.Direction

@JsonClass(generateAdapter = true)
data class DirectionJson(
    @Json(name = "routes")
    var routes: List<RouteJson> = listOf()
)

fun DirectionJson.toDomainModel(): Direction = Direction(routes.map { it.toDomainModel() })
