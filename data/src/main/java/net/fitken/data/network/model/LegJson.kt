package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LegJson(
    @Json(name = "end_address")
    var endAddress: String = "",
    @Json(name = "end_location")
    var endLocation: LocationJson = LocationJson(),
    @Json(name = "start_address")
    var startAddress: String = "",
    @Json(name = "start_location")
    var startLocation: LocationJson = LocationJson(),
    @Json(name = "steps")
    var steps: List<StepJson> = listOf()
)