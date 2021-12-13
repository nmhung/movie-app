package net.fitken.movieapp.base.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import net.fitken.movieapp.base.activity.BaseActivity

/**
 * This open class will have common function for Fragment
 */
open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    /**
     * This open method will detect if an fragment need to set light status bar or not
     * In any fragment that extends BaseFragment, may override this method
     * @return true if an fragment need to set light status bar
     * (because that fragment have background white),
     * false if an fragment does not need to set light status bar
     */

    open fun isNeedWindowLightStatusBar(): Boolean = true

    override fun onResume() {
        super.onResume()
        /**
         * Set light status bar if an fragment need it
         */
        if (isNeedWindowLightStatusBar()) {
            (requireActivity() as BaseActivity).setWindowLightStatus()
        } else {
            (requireActivity() as BaseActivity).clearWindowLightStatus()
        }
    }

    /**
     * Show a snack bar to display error message
     */
    fun showError(message: String?) {
        (requireActivity() as BaseActivity).showError(message)
    }
}