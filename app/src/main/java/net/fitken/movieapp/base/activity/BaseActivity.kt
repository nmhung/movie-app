package net.fitken.movieapp.base.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.snackbar.Snackbar

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
        val snackbar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        snackbar.show()
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