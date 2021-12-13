package net.fitken.feature.nowplaying.presentation

import net.fitken.feature.nowplaying.R
import net.fitken.feature.nowplaying.databinding.FragmentNowPlayingBinding
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment

class NowPlayingFragment: BaseFragment(R.layout.fragment_now_playing) {

    private val binding: FragmentNowPlayingBinding by viewBinding()
}