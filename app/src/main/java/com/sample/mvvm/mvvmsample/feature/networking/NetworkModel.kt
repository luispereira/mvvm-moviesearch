package com.sample.mvvm.mvvmsample.feature.networking

import com.sample.mvvm.mvvmsample.feature.shared.Constants
import com.vodafone.admin.mvc_movie_notify.features.networking.entities.UpcomingMovieResult
import com.vodafone.admin.mvc_movie_notify.features.networking.helpers.APIService
import com.vodafone.admin.mvc_movie_notify.features.networking.helpers.ApiClient
import io.reactivex.Observable

/**
 * @author lpereira on 24/07/2017.
 */

class NetworkModel(val service: APIService = ApiClient().getClient().create(APIService::class.java)) {

    fun upcomingMovies(): Observable<UpcomingMovieResult> {
        return service.upcomingMovies(Constants.TOKEN)
    }
}