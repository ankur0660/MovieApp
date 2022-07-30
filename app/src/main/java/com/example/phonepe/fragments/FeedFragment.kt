package com.example.phonepe.fragments

import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonepe.R
import com.example.phonepe.viewModels.FeedViewModel
import com.example.phonepe.adapters.MovieListAdapter
import com.example.phonepe.databinding.FragmentFeedBinding
import com.example.phonepe.helpers.plusAssign
import com.example.phonepe.models.MovieData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class FeedFragment : BaseViewBindingFragment<FragmentFeedBinding>() {

    companion object {
        var TAG = "FeedFragment"
        @JvmStatic
        fun newInstance() = FeedFragment()
    }

    private val viewModel: FeedViewModel by activityViewModels()
    var adapter: MovieListAdapter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFeedBinding = FragmentFeedBinding::inflate

    override fun setup() {
        fetchMoviesData()
    }

    private fun setState(state: STATE) {
        when (state) {
            STATE.LOADING -> {
                showLoading()
            }
            STATE.LOADED -> {
                hideLoading()
            }
            STATE.ERROR -> {
                hideLoading()
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    private fun setMovieAdapter(movieList: MutableList<MovieData>) {
        if (adapter == null)
            adapter = MovieListAdapter {
                var transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.add(R.id.root, MovieDescriptionFragment.newInstance(it), tag)
                transaction?.addToBackStack(null)
                transaction?.commit()

            }
        adapter?.setData(movieList)
        binding.rvMovie.layoutManager = LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.rvMovie.adapter = adapter

    }

    private fun fetchMoviesData() {
        subscriptions += viewModel.getMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setState(STATE.LOADING) }
            .subscribe({
                setMovieAdapter(movieList = it.toMutableList())
            }, {
                setState(STATE.ERROR)
            })
    }

    private enum class STATE {
        LOADING, LOADED, ERROR
    }

}