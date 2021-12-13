package net.fitken.movieapp.base.interceptors

import android.content.Context
import net.fitken.movieapp.base.exception.NoInternetException
import net.fitken.movieapp.base.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Connectivity Interceptor for okhttp client to return a network status message
 */

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isConnected(mContext)) {
            throw NoInternetException("No internet connection. Please check your connection and try again.")
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}