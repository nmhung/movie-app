package net.fitken.movieapp.base.interceptor

import net.fitken.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val QUERY_API_KEY = "api_key"

class BasicAuthInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter(QUERY_API_KEY, BuildConfig.API_TOKEN).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

}