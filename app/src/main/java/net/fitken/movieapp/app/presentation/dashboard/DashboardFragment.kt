package net.fitken.movieapp.app.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import net.fitken.movieapp.R
import net.fitken.movieapp.app.enums.MovieListTypeEnum
import net.fitken.movieapp.app.presentation.movielist.MovieListFragment
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentDashboardBinding
import javax.inject.Inject

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    private val binding: FragmentDashboardBinding by viewBinding()

    @Inject
    lateinit var navManager: NavManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.now_playing -> {
                    binding.navHostFragmentDashboard.findNavController()
                        .navigate(
                            R.id.nowPlayingFragment, MovieListFragment.createBundle(
                                MovieListTypeEnum.NOW_PLAYING
                            )
                        )
                }
                R.id.top_rated -> {
                    binding.navHostFragmentDashboard.findNavController()
                        .navigate(
                            R.id.topRatedFragment, MovieListFragment.createBundle(
                                MovieListTypeEnum.TOP_RATED
                            )
                        )
                }
            }
            true
        }

        // Disables reselection of bottom menu item, so fragments are not recreated when clicking on the same menu item
        binding.bottomNavigation.setOnItemReselectedListener { }
    }
}