package net.fitken.movieapp.base.exception

import com.squareup.moshi.JsonClass

/**
 * Server Exception for handling the json error response in header
 */
@JsonClass(generateAdapter = true)
class ServerException(var status: String, var message: String)