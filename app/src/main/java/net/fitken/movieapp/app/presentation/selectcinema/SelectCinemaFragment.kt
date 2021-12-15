package net.fitken.movieapp.app.presentation.selectcinema

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.setOnDebouncedClickListener
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentSelectCinemaBinding
import javax.inject.Inject

@AndroidEntryPoint
class SelectCinemaFragment : BaseFragment(R.layout.fragment_select_cinema) {

    private val binding: FragmentSelectCinemaBinding by viewBinding()

    @Inject
    lateinit var navManager: NavManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        binding.btnSelectCinema.setOnDebouncedClickListener {
            val action =
                SelectCinemaFragmentDirections.actionSelectCinemaFragmentToDashboardFragment()
            navManager.navigate(action)
        }
    }
}