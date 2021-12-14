package net.fitken.feature.nowplaying.data.network.service

import net.fitken.feature.nowplaying.data.network.response.GetNowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): GetNowPlayingResponse
}