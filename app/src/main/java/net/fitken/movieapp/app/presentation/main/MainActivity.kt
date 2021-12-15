package net.fitken.movieapp.app.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.app.presentation.dashboard.DashboardFragment
import net.fitken.movieapp.app.presentation.settings.SettingsFragment
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.navigateSafe
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavManager()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.first()
            currentFragment?.navigateSafe(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.first()
        if (currentFragment is SettingsFragment) return true

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.first()
        return when (item.itemId) {
            android.R.id.home -> {
                currentFragment?.findNavController()?.popBackStack()
                true
            }
            R.id.action_settings -> {
                currentFragment?.findNavController()?.navigate(R.id.settingsFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.first()
        if (currentFragment is DashboardFragment) {
            currentFragment.findNavController().navigate(R.id.selectCinemaFragment)
        } else {
            super.onBackPressed()
        }
    }
}