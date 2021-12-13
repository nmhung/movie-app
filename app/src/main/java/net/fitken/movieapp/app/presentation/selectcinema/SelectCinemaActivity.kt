package net.fitken.movieapp.app.presentation.selectcinema

import android.content.Intent
import android.os.Bundle
import net.fitken.movieapp.app.presentation.dashboard.DashboardActivity
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.setOnDebouncedClickListener
import net.fitken.movieapp.databinding.ActivitySelectCinemaBinding

class SelectCinemaActivity : BaseActivity() {

    private val binding: ActivitySelectCinemaBinding by viewBinding()

    override fun isNeedWindowLightStatusBar(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.btnSelectCinema.setOnDebouncedClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }
}