package net.fitken.feature.nowplaying.presentation

import android.os.Bundle
import android.view.View
import net.fitken.feature.nowplaying.R
import net.fitken.feature.nowplaying.databinding.FragmentNowPlayingBinding
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment

class NowPlayingFragment: BaseFragment(R.layout.fragment_now_playing) {

    private val binding: FragmentNowPlayingBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)
    }
}