package net.fitken.feature.nowplaying.domain

import java.io.Serializable

data class Movie(
    var posterPath: String? = null,
    var isAdult: Boolean = false,
    var overview: String? = null,
    var releaseDate: String? = null,
    var genres: List<Genre>? = null,
    var id: Int = 0,
    var originalTitle: String? = null,
    var originalLanguage: String? = null,
    var title: String? = null,
    var backdropPath: String? = null,
    var popularity: Float = 0f,
    var voteCount: Int = 0,
    var hasVideo: Boolean = false,
    var voteAverage: Float = 0f,
    var isLoaded: Boolean = false,
    var hasError: Boolean = false
) : Serializable

data class Genre(
    var id: Int,
    var name: String
) : Serializable