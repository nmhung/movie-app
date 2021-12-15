package net.fitken.movieapp.app.presentation.moviedetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.domain.model.Movie
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.observe
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.databinding.FragmentMovieDetailsBinding
import net.fitken.movieapp.itemMovieDetailsPoster

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    private val binding: FragmentMovieDetailsBinding by viewBinding()
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private var movie: Movie? = null

    override fun isNeedWindowLightStatusBar(): Boolean = false

    private val stateObserver = Observer<MovieDetailsViewModel.ViewState> {
        if (it.movie != null) {
            movie = it.movie
        }

        binding.isRefreshing = it.isLoading

        binding.rvItems.requestModelBuild()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        (requireActivity() as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as BaseActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.movie = args.movie
        binding.viewModel = viewModel
        observe(viewModel.stateLiveData, stateObserver)

        viewModel.init(args.movie.id)
        viewModel.loadData()

        binding.rvItems.withModels {
            itemMovieDetailsPoster {
                id("poster")
                movie(movie)
            }
        }
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