package net.fitken.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.fitken.domain.model.Location

@JsonClass(generateAdapter = true)
data class LocationJson(
    @Json(name = "lat")
    var lat: Double = 0.0,
    @Json(name = "lng")
    var lng: Double = 0.0
)

fun LocationJson.toDomainModel(): Location = Location(lat, lng)