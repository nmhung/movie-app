package net.fitken.movieapp.app.presentation.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.core.domain.model.Movie
import net.fitken.movieapp.R
import net.fitken.movieapp.app.enums.MovieListTypeEnum
import net.fitken.movieapp.app.listener.MovieListener
import net.fitken.movieapp.app.presentation.dashboard.DashboardFragmentDirections
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.observe
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentMovieListBinding
import net.fitken.movieapp.itemAdvertisement
import net.fitken.movieapp.itemMovie
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    companion object {
        const val KEY_MOVIE_TYPE = "movieType"

        fun createBundle(movieListTypeEnum: MovieListTypeEnum): Bundle = Bundle().apply {
            putSerializable(KEY_MOVIE_TYPE, movieListTypeEnum)
        }
    }

    private val binding: FragmentMovieListBinding by viewBinding()
    private val viewModel: MovieListViewModel by viewModels()

    @Inject
    lateinit var navManager: NavManager

    private var movies: List<Movie> = emptyList()

    private val stateObserver = Observer<MovieListViewModel.ViewState> {
        if (it.movies.isNotEmpty()) {
            movies = it.movies
        }
        if (it.error != null) showError(it.error)

        binding.isRefreshing = it.isRefreshing

        binding.rvItems.requestModelBuild()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieType = arguments?.getSerializable(KEY_MOVIE_TYPE) as MovieListTypeEnum?
            ?: MovieListTypeEnum.NOW_PLAYING
        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as BaseActivity).supportActionBar?.title =
            if (movieType == MovieListTypeEnum.NOW_PLAYING)
                getString(R.string.now_playing) else getString(R.string.top_rated)

        viewModel.init(movieType)
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
                            val action =
                                DashboardFragmentDirections
                                    .actionDashboardFragmentToMovieDetailsFragment(movie)
                            navManager.navigate(action)
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

        binding.rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rvItems.canScrollVertically(1)) {
                    viewModel.loadMore()
                }
            }
        })
    }
}