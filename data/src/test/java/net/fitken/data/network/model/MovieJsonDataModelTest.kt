package net.fitken.data.network.model

import net.fitken.data.common.Constants
import net.fitken.domain.model.Movie
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class MovieJsonDataModelTest {

    @Test
    fun `maps to MovieDomainModel`() {
        // given
        val genreJson = GenreJson(1, "Fiction")
        val movieJson = MovieJson(
            "poster.png",
            "overview here",
            "2021/12/12",
            3,
            "Test movie",
            "en",
            "Test Movie",
            "backdrop.png",
            0f,
            232,
            false,
            8.7f,
            arrayListOf(genreJson),
            130
        )

        // when
        val domainModel = movieJson.toDomainModel()

        // then
        domainModel shouldBeEqualTo Movie(
            Constants.URL_IMAGE + movieJson.posterPath,
            movieJson.overview,
            movieJson.releaseDate,
            movieJson.id,
            movieJson.originalTitle,
            movieJson.originalLanguage,
            movieJson.title,
            Constants.URL_IMAGE + movieJson.backdropPath,
            movieJson.popularity,
            movieJson.voteCount,
            movieJson.hasVideo,
            movieJson.voteAverage,
            movieJson.genres?.map { it.toDomainModel() },
            movieJson.runtime
        )
    }
}