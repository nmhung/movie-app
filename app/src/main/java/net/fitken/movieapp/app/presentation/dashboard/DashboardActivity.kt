package net.fitken.movieapp.app.presentation.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.navigateSafe
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.ActivityDashboardBinding
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private val binding: ActivityDashboardBinding by viewBinding()
    private val navController get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    @Inject
     lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        initBottomNavigation()
        initNavManager()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setupWithNavController(navController)

        // Disables reselection of bottom menu item, so fragments are not recreated when clicking on the same menu item
        binding.bottomNavigation.setOnItemReselectedListener { }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.first()
            currentFragment?.navigateSafe(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            showError("Open Settings")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}