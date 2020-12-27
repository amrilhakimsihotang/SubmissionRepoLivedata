package com.amrilhakimsihotang.submissionrepolivedata.data.source.remote

import android.os.Handler
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.MovieResponse
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.TiviResponse
import com.amrilhakimsihotang.submissionrepolivedata.utils.EspressoIdlingResource
import com.amrilhakimsihotang.submissionrepolivedata.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllMovie(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
                {

                    callback.onAllMovieReceived(jsonHelper.loadMovie())
                    EspressoIdlingResource.decrement()
                },
                SERVICE_LATENCY_IN_MILLIS
        )
    }


    fun getAllTivi(callback: LoadTiviCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
                {
                    callback.onAllTiviReceived(jsonHelper.loadTivi())
                    EspressoIdlingResource.decrement()
                },
                SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadTiviCallback {
        fun onAllTiviReceived(tiviResponse: List<TiviResponse>)
    }
    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: List<MovieResponse>)
    }

}

