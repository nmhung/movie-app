package net.fitken.movieapp.app.presentation.moviedetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.databinding.ActivityMovieDetailsBinding

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {

    private val binding: ActivityMovieDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.action_settings -> {
            showError("Open Settings")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}