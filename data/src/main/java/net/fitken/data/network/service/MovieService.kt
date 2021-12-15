package net.fitken.data.network.service

import net.fitken.data.network.model.NowPlayingJson
import net.fitken.data.network.model.TopRatedJson
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): NowPlayingJson

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): TopRatedJson
}