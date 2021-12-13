package net.fitken.movieapp.base.exception

import java.io.IOException

/**
 * No Internet Exception for okhttp interceptor
 */
class NoInternetException(message: String) : IOException(message)