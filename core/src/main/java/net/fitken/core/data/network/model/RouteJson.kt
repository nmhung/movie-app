package net.fitken.core.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.fitken.core.data.network.model.LegJson

@JsonClass(generateAdapter = true)
data class RouteJson(
    @Json(name = "legs")
    var legs: List<LegJson> = listOf()
)