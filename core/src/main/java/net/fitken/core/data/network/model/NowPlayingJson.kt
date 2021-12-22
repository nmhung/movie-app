package net.fitken.core.data.network.model

import com.squareup.moshi.Json
import net.fitken.core.data.network.model.MovieJson

data class NowPlayingJson(
    var page: Int,
    @Json(name = "total_results")
    var totalResults: Int,
    @Json(name = "total_pages")
    var totalPages: Int,
    var results: List<MovieJson>
)