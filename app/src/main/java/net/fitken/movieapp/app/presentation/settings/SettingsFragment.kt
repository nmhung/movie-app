package net.fitken.movieapp.app.presentation.settings

import android.os.Bundle
import android.view.View
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as BaseActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}