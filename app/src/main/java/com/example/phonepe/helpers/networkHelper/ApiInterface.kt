package com.example.phonepe.helpers.networkHelper

import com.example.phonepe.models.MoviesModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Observable<MoviesModel>
}