package com.example.phonepe.repositories

import com.example.phonepe.helpers.networkHelper.RetrofitClient
import com.example.phonepe.models.MoviesModel
import io.reactivex.rxjava3.core.Single


class MovieRepo {

    private val networkClient = RetrofitClient()

    fun getMoviesData(): Single<MoviesModel> {
        return Single.fromObservable(networkClient.getApiService().getMovies(RetrofitClient.API_KEY))
    }
}