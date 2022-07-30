package com.example.phonepe.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.phonepe.models.MovieData
import com.example.phonepe.repositories.MovieRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class FeedViewModel : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private var movieRepo = MovieRepo()

    fun getMoviesList(): Single<List<MovieData>> {
        return movieRepo.getMoviesData().flatMap {
            Single.just(it.moviesList)
        }
            .subscribeOn(Schedulers.io())
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }
}