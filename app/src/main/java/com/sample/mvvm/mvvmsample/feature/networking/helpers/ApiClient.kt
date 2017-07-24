package com.vodafone.admin.mvc_movie_notify.features.networking.helpers

import com.sample.mvvm.mvvmsample.feature.shared.Constants
import retrofit2.Retrofit

/**
 * @author lpereira on 28/06/2017.
 */

class ApiClient {
    private val TIMEOUT: Long = 30

    fun getClient(): retrofit2.Retrofit {
        val builder = okhttp3.OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)

        val okHttpClient = builder.build()

        return retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .build()
    }
}
