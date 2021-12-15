package net.fitken.movieapp.app.presentation.toprated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.domain.model.Movie
import net.fitken.movieapp.R
import net.fitken.movieapp.app.listener.MovieListener
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.observe
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentTopRatedBinding
import net.fitken.movieapp.itemAdvertisement
import net.fitken.movieapp.itemMovie
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : BaseFragment(R.layout.fragment_top_rated) {

    private val binding: FragmentTopRatedBinding by viewBinding()
    private val viewModel: TopRatedViewModel by viewModels()

    @Inject
    lateinit var navManager: NavManager

    private var movies: List<Movie> = emptyList()

    private val stateObserver = Observer<TopRatedViewModel.ViewState> {
        if (it.movies.isNotEmpty()) {
            movies = it.movies
        }

        binding.isRefreshing = it.isLoading

        binding.rvItems.requestModelBuild()
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
                    listener(object : MovieListener {
                        override fun onClicked() {
                        }

                    })
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