package net.fitken.feature.toprated.data.network.model

import com.squareup.moshi.Json

data class TopRatedJson(
    var page: Int,
    @Json(name = "total_results")
    var totalResults: Int,
    @Json(name = "total_pages")
    var totalPages: Int,
    var results: List<MovieJson>
)