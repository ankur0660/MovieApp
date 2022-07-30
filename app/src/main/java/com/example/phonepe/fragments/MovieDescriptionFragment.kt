package com.example.phonepe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.phonepe.R
import com.example.phonepe.databinding.FragmentFeedBinding
import com.example.phonepe.databinding.FragmentMovieDescriptionBinding
import com.example.phonepe.helpers.click
import com.example.phonepe.models.MovieData

class MovieDescriptionFragment : BaseViewBindingFragment<FragmentMovieDescriptionBinding>() {
    companion object {
        var MOVIE_DATA = "MOVIE_DATA"

        @JvmStatic
        fun newInstance(movieData: MovieData) =
            MovieDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MOVIE_DATA, movieData)
                }
            }
    }

    /**
     * movie data to show
     */
    var movieData: MovieData? = null


    override fun setup() {
        movieData = arguments?.getSerializable(MOVIE_DATA) as MovieData
        initViews()
    }

    fun initViews() {
        with(binding) {
            movieData?.let {
                overviewContent.text = it.overview
                tvDate.text = it.releaseDate
                tvRatingValue.text = it.voteAverage?.toString() ?: "0.0"
                tvPopularityValue.text = it.popularity.toString()
                tvTitle.text = it.title
                Glide.with(this@MovieDescriptionFragment)
                    .load(it.getImagePath())
                    .error(R.drawable.error_placeholder)
                    .into(ivDescThumbnail)
            }
            backButton.click {
                activity?.onBackPressed()
            }
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDescriptionBinding = FragmentMovieDescriptionBinding::inflate

}