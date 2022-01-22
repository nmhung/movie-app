package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StepJson(
    @Json(name = "end_location")
    var endLocation: LocationJson = LocationJson(),
    @Json(name = "start_location")
    var startLocation: LocationJson = LocationJson()
)