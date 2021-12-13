package net.fitken.movieapp.base.extension

import android.os.SystemClock
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

/**
 * Extension function for debouncing view on click event
 */
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebouncer = ActionDebouncer(action)

    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

/**
 * Extension function for removing debouncing view on click event
 */
fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

/**
 * This class will handle the debouncing action for view's click event
 */
private class ActionDebouncer(private val action: () -> Unit) {

    companion object {
        const val DEBOUNCE_INTERNAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    /**
     * Action will be invoked if the duration between two click event greater than the
     * DEBOUNCE_INTERNAL_MILLISECONDS
     */
    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERNAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}

/**
 * Extension property for view's visibility
 */
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

/**
 * Extension function to set hide a view
 * parameter: if gone equals true then the view will be gone else the view will be visible
 */
fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else VISIBLE
}

/**
 * Extension function to set visible for a view
 */
fun View.show() {
    visibility = VISIBLE
}