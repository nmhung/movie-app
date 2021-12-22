package net.fitken.movieapp.base.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.Moshi
import net.fitken.movieapp.R
import net.fitken.core.data.network.exception.ServerException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Create an abstract class for Activity so that every activity can extends and have the common functions
 */

abstract class BaseActivity : AppCompatActivity() {

    /**
     * This open method will detect if an activity need to set light status bar or not
     * In any activity that extends BaseActivity, may override this method
     * @return true if an activity need to set light status bar
     * (because that activity have background white),
     * false if an activity does not need to set light status bar
     */
    open fun isNeedWindowLightStatusBar(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Set light status bar if an activity need it
         */
        if (isNeedWindowLightStatusBar()) {
            setWindowLightStatus()
        } else {
            clearWindowLightStatus()
        }
    }

    /**
     * Show a snack bar to display error message
     */
    fun showError(message: String?) {
        if (message == null) return
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    fun showError(e: Throwable) {
        Snackbar.make(findViewById(android.R.id.content), getErrorMessage(e), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun getErrorMessage(e: Throwable): String {
        if (e is HttpException) return getHttpExceptionMessage(e)
        if (e is SocketTimeoutException) return getString(R.string.error_timeout)
        if (e is IOException) return e.message ?: getString(R.string.error_unknown)
        return getString(R.string.error_unknown)
    }

    private fun getHttpExceptionMessage(httpException: HttpException): String {
        try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(ServerException::class.java)

            val serverException =
                jsonAdapter.fromJson(httpException.response()?.errorBody()?.string() ?: "")
                    ?: return String.format(
                        getString(R.string.error_http),
                        "${httpException.code()}"
                    )

            return serverException.message
        } catch (e: Exception) {
            e.printStackTrace()
            return String.format(getString(R.string.error_http), "${httpException.code()}")
        }
    }

    /**
     * Hide keyboard programmatically by code
     */
    fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Clear window light status bar to make the status bar icon appear on black background
     */
    fun clearWindowLightStatus() {
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = false
    }

    /**
     * Clear window light status bar to make the status bar icon appear on white background
     */
    fun setWindowLightStatus() {
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true
    }
}