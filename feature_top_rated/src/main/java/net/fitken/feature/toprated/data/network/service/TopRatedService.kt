package net.fitken.feature.toprated.data.network.service

import net.fitken.feature.toprated.data.network.model.TopRatedJson
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedService {
    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): TopRatedJson
}