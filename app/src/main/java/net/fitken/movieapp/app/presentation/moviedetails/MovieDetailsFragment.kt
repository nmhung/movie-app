package net.fitken.movieapp.app.presentation.moviedetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.databinding.FragmentMovieDetailsBinding

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    private val binding: FragmentMovieDetailsBinding by viewBinding()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun isNeedWindowLightStatusBar(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        (requireActivity() as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as BaseActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.movie = args.movie
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            findNavController().popBackStack()
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