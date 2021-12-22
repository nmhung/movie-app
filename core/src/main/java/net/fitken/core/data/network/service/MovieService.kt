package net.fitken.core.data.network.service

import net.fitken.core.data.network.model.MovieJson
import net.fitken.core.data.network.model.NowPlayingJson
import net.fitken.core.data.network.model.TopRatedJson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): NowPlayingJson

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): TopRatedJson

    @GET("movie/{movie_id}?append_to_response=videos,credits,reviews")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): MovieJson
}