package net.fitken.feature.toprated.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.EntryPointAccessors
import net.fitken.feature.toprated.R
import net.fitken.feature.toprated.databinding.FragmentTopRatedBinding
import net.fitken.feature.toprated.domain.model.Movie
import net.fitken.feature.toprated.domain.usecase.GetTopRatedUseCase
import net.fitken.feature.toprated.itemMovie
import net.fitken.movieapp.app.di.AppModuleDependencies
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.observe
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.itemAdvertisement
import javax.inject.Inject

class TopRatedFragment : BaseFragment(R.layout.fragment_top_rated) {

    private val binding: FragmentTopRatedBinding by viewBinding()
    private val viewModel: TopRatedViewModel by viewModels()

    private var movies: List<Movie> = emptyList()

    private val stateObserver = Observer<TopRatedViewModel.ViewState> {
        if (it.movies.isNotEmpty()) {
            movies = it.movies
        }

        binding.isRefreshing = it.isLoading

        binding.rvItems.requestModelBuild()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerPresentationComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    AppModuleDependencies::class.java
                )
            )
            .build().inject(this)

        super.onCreate(savedInstanceState)
    }

    @Inject
    fun setViewModelAttributes(getTopRatedUseCase: GetTopRatedUseCase) {
        viewModel.setViewModelAttributes(getTopRatedUseCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        binding.viewModel = viewModel
        observe(viewModel.stateLiveData, stateObserver)

        viewModel.loadData()

        binding.rvItems.withModels {
            movies.forEachIndexed { index, movie ->
                itemMovie {
                    id("movie $index")
                    movie(movie)
                    voteCount(getString(net.fitken.movieapp.R.string.vote_count, movie.voteCount))
                }


                if ((index + 1) % 3 == 0) {
                    itemAdvertisement {
                        id("ads $index")
                    }
                }
            }
        }
    }
}