package net.fitken.movieapp.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * This class will detect network connectivity based on wifi or mobile data
 */
class NetworkUtil {

    companion object {
        @SuppressLint("MissingPermission")
        fun isConnected(context: Context): Boolean {
            val cm: ConnectivityManager? =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm?.activeNetworkInfo
            return null != activeNetwork && activeNetwork.isConnected
        }
    }

}