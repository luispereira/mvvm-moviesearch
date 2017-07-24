package com.sample.mvvm.mvvmsample.feature.movies

import com.sample.mvvm.mvvmsample.feature.networking.NetworkModel
import com.vodafone.admin.mvc_movie_notify.features.networking.entities.MovieEntity
import com.vodafone.admin.mvc_movie_notify.features.networking.entities.UpcomingMovieResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author lpereira on 24/07/2017.
 */

class MovieViewModel(val networkModel: NetworkModel = NetworkModel()) {

    fun fetchMovies(): io.reactivex.Observable<List<MovieEntity>> {
        return networkModel.upcomingMovies()
                .flatMap { t: UpcomingMovieResult? -> Observable.just(t?.results ?: Collections.emptyList()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}
