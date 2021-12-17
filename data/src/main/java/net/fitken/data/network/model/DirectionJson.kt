package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.fitken.domain.model.*

@JsonClass(generateAdapter = true)
data class DirectionJson(
    @Json(name = "routes")
    var routes: List<RouteJson> = listOf()
)

fun StepJson.toDomainModel(): Step = Step(
    endLocation.toDomainModel(),
    startLocation.toDomainModel()
)

fun LocationJson.toDomainModel(): Location = Location(lat, lng)

fun LegJson.toDomainModel(): Leg =
    Leg(
        endAddress,
        endLocation.toDomainModel(),
        startAddress,
        startLocation.toDomainModel(),
        steps.map { it.toDomainModel() }
    )

fun RouteJson.toDomainModel(): Route = Route(legs.map { it.toDomainModel() })

fun DirectionJson.toDomainModel(): Direction = Direction(routes.map { it.toDomainModel() })
