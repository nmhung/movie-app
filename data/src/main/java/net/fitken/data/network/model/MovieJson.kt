package net.fitken.data.network.model

import com.squareup.moshi.Json
import net.fitken.data.common.Constants
import net.fitken.domain.model.Genre
import net.fitken.domain.model.Movie

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

    var genres: List<GenreJson>? = ArrayList(),

    var runtime: Int? = null
)

data class GenreJson(
    var id: Int,
    var name: String
)

fun MovieJson.toDomainModel(): Movie = Movie(
    Constants.URL_IMAGE + this.posterPath,
    this.overview,
    this.releaseDate,
    this.id,
    this.originalTitle,
    this.originalLanguage,
    this.title,
    Constants.URL_IMAGE + this.backdropPath,
    this.popularity,
    this.voteCount,
    this.hasVideo,
    this.voteAverage,
    this.genres?.map { it.toDomainModel() },
    this.runtime
)

fun GenreJson.toDomainModel(): Genre = Genre(
    this.id,
    this.name
)