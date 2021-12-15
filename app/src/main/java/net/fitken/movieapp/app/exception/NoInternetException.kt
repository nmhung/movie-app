package net.fitken.movieapp.app.data.network.exception

import java.io.IOException

/**
 * No Internet Exception for okhttp interceptor
 */
class NoInternetException(message: String) : IOException(message)