package net.fitken.feature.toprated.data.network.model

import com.squareup.moshi.Json
import net.fitken.feature.toprated.data.Constants
import net.fitken.feature.toprated.domain.model.Movie

data class MovieJson(
    @Json(name = "poster_path")
    var posterPath: String? = null,

    var overview: String? = null,

    @Json(name = "release_date")
    var releaseDate: String? = null,

    var id: Int = 0,

    @Json(name = "original_title")
    var originalTitle: String? = null,

    @Json(name = "original_language")
    var originalLanguage: String? = null,

    var title: String? = null,

    @Json(name = "backdrop_path")
    var backdropPath: String? = null,

    var popularity: Float = 0f,

    @Json(name = "vote_count")
    var voteCount: Int = 0,

    @Json(name = "video")
    var hasVideo: Boolean = false,

    @Json(name = "vote_average")
    var voteAverage: Float = 0f,
)

fun MovieJson.toDomainModel(): Movie = Movie(
    Constants.URL_IMAGE + this.posterPath,
    this.overview,
    this.releaseDate,
    this.id,
    this.originalTitle,
    this.originalLanguage,
    this.title,
    this.backdropPath,
    this.popularity,
    this.voteCount,
    this.hasVideo,
    this.voteAverage,
)