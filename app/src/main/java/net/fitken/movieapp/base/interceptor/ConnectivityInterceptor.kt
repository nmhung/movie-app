package net.fitken.movieapp.base.interceptor

import android.content.Context
import net.fitken.movieapp.app.utils.NetworkUtil
import net.fitken.movieapp.base.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Connectivity Interceptor for okhttp client to return a network status message
 */

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isInternetAvailable(mContext)) {
            throw NoInternetException("No internet connection. Please check your connection and try again.")
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}