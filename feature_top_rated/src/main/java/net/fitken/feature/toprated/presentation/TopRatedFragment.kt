package net.fitken.feature.toprated.presentation

import android.os.Bundle
import android.view.View
import net.fitken.feature.toprated.R
import net.fitken.feature.toprated.databinding.FragmentTopRatedBinding
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment

class TopRatedFragment : BaseFragment(R.layout.fragment_top_rated) {

    private val binding: FragmentTopRatedBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)
    }
}